package qdu.java.recruit.controller.hr;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qdu.java.recruit.constant.GlobalConst;
import qdu.java.recruit.controller.BaseController;
import qdu.java.recruit.entity.HREntity;
import qdu.java.recruit.entity.ResumeEntity;
import qdu.java.recruit.entity.UserEntity;
import qdu.java.recruit.pojo.ApplicationResumeHRBO;
import qdu.java.recruit.pojo.PostedRecumeBO;
import qdu.java.recruit.service.ApplicationService;
import qdu.java.recruit.service.ResumeService;
import qdu.java.recruit.service.UserService;
import qdu.java.recruit.util.StateUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;



@RestController
@Api(value = "Resume接口",description = "Resume接口")
public class ResumeController extends BaseController {

    @Autowired
    ResumeService resumeService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    UserService userService;

    /**
     *   查询简历
     *   通过state判断查询的简历状态，返回相应状态的简历
     *   可查询新、备选、放弃、未通过、通过、一面、二面、三面，其余大于三面的简历
     *      0       1     -1    -2      -3     2    3     4         >4
     *   @author  PocketKnife
     *   @create  23:40 2020/5/9
     *
     *   5/17 16：42  陈淯
     *   添加按照 状态+title查找
     *
     *   5/18 陈淯
     *   添加了分页
     *   5/19
     *   添加查询其余大于三面的简历
    */
    @GetMapping(value = "/hr/resume/{state}")
    @ResponseBody
    public String getResume(HttpServletRequest request , @PathVariable int state,
                            @RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "limit", defaultValue = "6") int limit){
        HREntity hr = this.getHR(request);
        if(hr == null) {
            return errorDirect_404();
        }
        page = page < 1 || page > GlobalConst.MAX_PAGE ? 1 : page;

        List<Integer> positionIds = (List<Integer>)request.getSession().getAttribute("positionId");

        PageInfo<PostedRecumeBO> resumes = null;

        if (positionIds==null){
            resumes = resumeService.getResumeByState(hr.getHrId(),state,page,limit);  //直接按状态查找
        }else {
            resumes = resumeService.getResumeByStateWithPosIds(hr.getHrId(),state,positionIds,page,limit);//按状态+标题（查出List positionId）
        }

        Map output = new TreeMap();
        output.put("resumes",resumes);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();

    }

    /**
     *   查看所有简历
     *   @author  PocketKnife
     *   @create  23:40 2020/5/9
     *
     *   5/17 16：42  陈淯
     *   添加按照 状态+title查找
     *
     *   5/18 陈淯
     *   添加了分页
    */
    @ResponseBody
    @GetMapping("/hr/resumeInfo")
    public String ResumeInfo(HttpServletRequest request,
                             @RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "limit", defaultValue = "6") int limit) {
        HREntity hr = this.getHR(request);
        if (hr == null) {
            return errorDirect_404();
        }
        page = page < 1 || page > GlobalConst.MAX_PAGE ? 1 : page;

        List<Integer> positionIds = (List<Integer>)request.getSession().getAttribute("positionId");

        PageInfo<PostedRecumeBO> resumes = null;

        if (positionIds==null){
            System.out.println("aaaaaa"+hr.getHrId());
            resumes = resumeService.getAllResume(hr.getHrId(),page,limit);  //直接按状态查找
        }else {
            System.out.println("111111"+hr.getHrId());
            resumes = resumeService.getAllResumeWithPosIds(hr.getHrId(),positionIds,page,limit);//按状态+标题（查出List positionId）
        }
        Map output = new TreeMap();
        output.put("resumeInfo",resumes);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
        }


    /**
     * 移除简历（条件不符合 没有通过面试的）
     */
    @PutMapping("/hr/removeresume/{applicationId}")
    public String RemoveResume(HttpServletRequest request,
                               @PathVariable int applicationId) {
        HREntity hr = this.getHR(request);
        if (hr == null) {
            return errorDirect_404();
        }
        if (applicationService.updateResumeState(-1,applicationId)==0){
           return errorDirect_404();
        }
        return "查看简历界面";
    }

    /**
     * 安排面试    flag=1 代表 1面
     */
    @PutMapping("/hr/interview/{applicationId}")
    public String arrangeInterview(HttpServletRequest request, @RequestParam int flag,@RequestParam String interviewsDesc,@PathVariable int applicationId ) {
        HREntity hr = this.getHR(request);
        String interviewDesc = interviewsDesc +"("+ flag + "面）";
        if(hr == null) {
            return errorDirect_404();
        }
        if (applicationService.arrangeInterview(applicationId,(flag+1))==0){
            return errorDirect_404();
        }else {
            if(!resumeService.sendNews((flag+1), applicationId, interviewDesc, hr.getHrId()))
                return "发送信息失败";
            else
                return "成功";
        }
    }

    /**
     *   查看简历具体信息
     *   @author  PocketKnife
     *   @create  22:09 2020/5/9
    */
    @PostMapping(value = "/hr/resume/ResumeDesc")
    @ResponseBody
    public String getResumeDesc(HttpServletRequest request,@RequestParam(value="applicationId",defaultValue = "")int applicationId){
        HREntity hr = this.getHR(request);
        if(hr == null) {
            return errorDirect_404();
        }
        ApplicationResumeHRBO applicationResumeHRBO = applicationService.getResumeHRBO(applicationId);
        UserEntity userEntity = userService.getUser(applicationResumeHRBO.getUserId());
        ResumeEntity resumeEntity = resumeService.getResumeById(applicationResumeHRBO.getResumeId());
        //简历状态未看时 才更新简历状态未备选
        if(applicationResumeHRBO.getState() == 0){
        applicationService.updateResumeState(1,applicationId);
        }

        Map output = new TreeMap();
        output.put("userEntity",userEntity);
        output.put("resumeEntity",resumeEntity);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
    }


    //==========================下面功能新增5/19==========================

    /**
     * 简历搜索功能
     * 黄少龙
     * 查询所有的简历按keyword
     * 5/19
     */
    @PostMapping("/hr/search")
    @ResponseBody
    public String ResumeSearch(HttpServletRequest request, @RequestParam(value = "keyword", defaultValue = "") String keyword,
                               @RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "limit", defaultValue = "6") int limit) {
        HREntity hr = this.getHR(request);
        if (hr == null) {
            return errorDirect_404();
        }
        request.getSession().setAttribute("keyword",keyword);
        page = page < 1 || page > GlobalConst.MAX_PAGE ? 1 : page;
        PageInfo<PostedRecumeBO> posInfo = resumeService.searchUser(hr.getHrId(), keyword, page, limit);

        Map output = new TreeMap();
        output.put("hr", hr);
        output.put("title", ("第" + page + "页"));
        output.put("keyword", keyword);
        output.put("posInfo", posInfo);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }
    /**
     * 简历搜索功能
     * 黄少龙
     * 查询所有的简历按keyword+标题+状态
     * 5/19
     */
    @GetMapping(value = "/hr/search/{state}")
    @ResponseBody
    public String ResumeSearchByState(HttpServletRequest request,
                                      @PathVariable int state,
                                      @RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "limit", defaultValue = "6") int limit) {
        HREntity hr = this.getHR(request);
        if (hr == null) {
            return errorDirect_404();
        }
        String keyword=(String)request.getSession().getAttribute("keyword");
        page = page < 1 || page > GlobalConst.MAX_PAGE ? 1 : page;
        List<Integer> positionIds = (List<Integer>) request.getSession().getAttribute("positionId");
        PageInfo<PostedRecumeBO> resumes = null;
        if (positionIds==null) {
            resumes = resumeService.getResumeByTitleAndState(hr.getHrId(), keyword,state, page, limit);  //直接按状态查找
        } else {
            resumes = resumeService.getResumeByTitleAndStateWithPosIds(hr.getHrId(), state, positionIds,keyword, page, limit);//按状态+标题（查出List positionId）
        }
        Map output = new TreeMap();
        output.put("resumes", resumes);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();

    }

    /**
     * 发送信息   包括发送offer 和 通知面试未通过
     * //5/18 陈淯   合并 发送offer 和 通知面试未通过
     */
    @PutMapping("/hr/sendNews/{applicationId}")
    public String sendOffers(HttpServletRequest request,
                             @PathVariable int applicationId,
                             @RequestParam("interviewsDesc") String interviewsDesc,
                             @RequestParam("state") int state) {
        HREntity hr = this.getHR(request);

        if (hr == null) {
            return errorDirect_404();
        }
        if(!resumeService.sendNews(state, applicationId, interviewsDesc, hr.getHrId())){
            return "发送失败";
        }

        return "成功";
    }
}
