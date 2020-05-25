package qdu.java.recruit.controller.hr;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qdu.java.recruit.constant.GlobalConst;
import qdu.java.recruit.controller.BaseController;
import qdu.java.recruit.entity.HREntity;
import qdu.java.recruit.pojo.InterviewDescBO;
import qdu.java.recruit.service.ApplicationService;
import qdu.java.recruit.service.InterviewService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ChenGuiHong
 * @create 2020-05-18  10:17
 */
@RestController
@Api(value = "面试管理",description = "面试管理接口")
public class InterviewController extends BaseController{

    @Autowired
    InterviewService interviewService;

    @Autowired
    ApplicationService applicationService;
    /**
     *  查看正在安排面试的简历
     *  点击面试管理，默认，下拉框未选中，查看所有轮次，所有职位的面试管理信息，
     *  下拉框选中，点击正在面试，查看所有轮次，对应职位的面试管理信息，
     *  按照简历的state降序，message的msgSendTime排序
     *   return  news , state ,time, ischeck,msgSendTime , applicationId, userEntity , title
     *   @author  ChenGuiHong
     *   @create  10:23 2020/5/18
    */
    @PostMapping("hr/interview/all")
    @ResponseBody
    public String showAllInterview(HttpServletRequest request,
                                   @RequestParam(value="page",defaultValue = "1")int page,
                                   @RequestParam(value="limit",defaultValue="12") int limit){
        HREntity hr = this.getHR(request);
        if (hr == null) {
            this.errorDirect_404();
        }
        page = page < 1 || page > GlobalConst.MAX_PAGE ? 1 : page;
        List<Integer> positionIds =  (ArrayList<Integer>)request.getSession().getAttribute("positionId");
        PageInfo<InterviewDescBO> interviews = interviewService.listInterviewInfos(hr.getHrId(),page,limit,2,positionIds);
        Map output = new TreeMap();
        output.put("title", ("第" + page + "页"));
        output.put("hr", hr);
        output.put("interviews",interviews);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
    }

    /**
     *   下拉框选中后，点击面试轮次查看对应轮次，职位的面试管理信息，
     *   分为一面，二面，三面；state = 2 | 3 | 4
     *   下拉框未选中，点击面试轮次查看对应轮次，所有职位的面试管理信息，
     *   ////默认显示的是对应职位下所有正在面试的简历
     *   jumpToResumePage获得applicationId列表
     *   request : title
     *
     *   @author  ChenGuiHong 
     *   @create  22:59 2020/5/18
    */
    @PostMapping("hr/interview/category/")
    @ResponseBody
    public String showPositionIntterview(HttpServletRequest request,@RequestParam(value = "state") int state,
                                         @RequestParam(value="page",defaultValue = "1")int page,
                                         @RequestParam(value="limit",defaultValue="12") int limit
                                         ){
       HREntity hr = this.getHR(request);
       if(hr == null) {
            return errorDirect_404();
        }
        page = page < 1 || page > GlobalConst.MAX_PAGE ? 1 : page;

        List<Integer> positionIds =  (ArrayList<Integer>)request.getSession().getAttribute("positionId");

        PageInfo<InterviewDescBO> interviews = interviewService.listInterviewInfoByState(hr.getHrId(),page,limit,state,positionIds);
        Map output = new TreeMap();
        output.put("title", ("第" + page + "页"));
        output.put("hr", hr);
        output.put("interviews",interviews);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
    }

    /**
     *
     *  查看 更多 （四面之后的面试轮次，state >= 5）
     *  下拉框未选中，点击更多，查看更多轮次，所有职位的面试管理信息，
     *  下拉框选中，点击更多，查看更多轮次，对应职位的面试管理信息，
     *  按照简历的state降序，message的msgSendTime排序
     *   return  news , state , msgSendTime , applicationId, userEntity , title
     *   @author  ChenGuiHong
     *   @create  10:23 2020/5/18
     */
    @PostMapping("hr/interview/more")
    @ResponseBody
    public String showMoreInterview(HttpServletRequest request,
                                   @RequestParam(value="page",defaultValue = "1")int page,
                                   @RequestParam(value="limit",defaultValue="12") int limit){
       HREntity hr = this.getHR(request);
       if (hr == null) {
           this.errorDirect_404();
       }
        page = page < 1 || page > GlobalConst.MAX_PAGE ? 1 : page;
        List<Integer> positionIds =  (ArrayList<Integer>)request.getSession().getAttribute("positionId");
        PageInfo<InterviewDescBO> interviews = interviewService.listInterviewInfos(hr.getHrId(),page,limit,5,positionIds);
        Map output = new TreeMap();
        output.put("title", ("第" + page + "页"));
        output.put("hr", hr);
        output.put("interviews",interviews);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
    }


    /**
     * 移除简历,进入人才库（条件不符合或者面试不通过）
     */
    @PutMapping("/hr/abandon/{applicationId}")
    public String RemoveResume(HttpServletRequest request, @PathVariable int applicationId) {
        HREntity hr = this.getHR(request);
        if(hr == null) {
           return errorDirect_404();
        }
        if (applicationService.updateResumeState(-2,applicationId)==0){
            return errorDirect_404();
        }
        return "简历被放弃，并放入人才库";
    }

}
