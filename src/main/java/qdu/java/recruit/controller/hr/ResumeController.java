package qdu.java.recruit.controller.hr;

import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import qdu.java.recruit.controller.BaseController;
import qdu.java.recruit.entity.HREntity;
import qdu.java.recruit.entity.ResumeEntity;
import qdu.java.recruit.entity.UserEntity;
import qdu.java.recruit.pojo.ApplicationResumeHRBO;
import qdu.java.recruit.pojo.PostedRecumeBO;
import qdu.java.recruit.service.ApplicationService;
import qdu.java.recruit.service.ResumeService;
import qdu.java.recruit.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
@Api(value = "Resume接口")
public class ResumeController extends BaseController {

    @Autowired
    ResumeService resumeService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    UserService userService;

    @PostMapping("/hr/applyInfo")
    public String ResumeInfo(HttpServletRequest request) {
        HREntity hr = this.getHR(request);
        if(hr == null) {
            return errorDirect_404();
        }
        return null;
    }

    /**
     *   查看新简历
     *   根据HRId查询投递到该hr下的各个岗位的投递者的信息。
     *   @author  PocketKnife 
     *   @create  12:12 2020/5/9
    */
    @PostMapping(value = "/hr/resume/newresume")
    @ResponseBody
    public String getResume(HttpServletRequest request){

        HREntity hr = this.getHR(request);
        if (hr == null) {
            this.errorDirect_404();
        }
        List<PostedRecumeBO> newRecumeBOList = resumeService.getResumeByHrId(hr.getHrId());
        Map output = new TreeMap();
        output.put("NewResumeList",newRecumeBOList);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
    }
    
    /**
     *   查看备选的简历
     *   根据hrId查询备选的简历，即以及看过的简历
     *   @author  PocketKnife 
     *   @create  19:04 2020/5/9
    */
    @PostMapping(value = "/hr/resume/seenresume")
    @ResponseBody
    public String getSeenResume(HttpServletRequest request){
        HREntity hr = this.getHR(request);
        if (hr == null) {
            this.errorDirect_404();
        }
        List<PostedRecumeBO> seenRecumeBOList = resumeService.getSeenResumeByHrId(hr.getHrId());
        Map output = new TreeMap();
        output.put("NewResumeList",seenRecumeBOList);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();

    }

    /**
     *   查看正在安排面试的简历
     *   根据hrId查询正在面试中的简历
     *   @author  PocketKnife
     *   @create  19:11 2020/5/9
    */
    @PostMapping(value = "/hr/resume/interviewresume")
    @ResponseBody
    public String getInterviewResume(HttpServletRequest request){
        HREntity hr = this.getHR(request);
        if (hr == null) {
            this.errorDirect_404();
        }
        List<PostedRecumeBO> interviewRecumeBOList = resumeService.getInterviewResumeByHrId(hr.getHrId());
        Map output = new TreeMap();
        output.put("NewResumeList",interviewRecumeBOList);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
    }

    /**
     *   查看未通过的简历
     *   @author  PocketKnife
     *   @create  19:11 2020/5/9
     */
    @PostMapping(value = "/hr/resume/failedresume")
    @ResponseBody
    public String getFailedResume(HttpServletRequest request){
        HREntity hr = this.getHR(request);
        if (hr == null) {
            this.errorDirect_404();
        }
        List<PostedRecumeBO> failedRecumeBOList = resumeService.getFailedResumeByHrId(hr.getHrId());
        Map output = new TreeMap();
        output.put("NewResumeList",failedRecumeBOList);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
    }

    /**
     *   查看未通过的简历
     *   @author  PocketKnife
     *   @create  21:40 2020/5/9
    */
    @PostMapping(value = "/hr/resume/abandonresume")
    @ResponseBody
    public String getAbandonResume(HttpServletRequest request){
        HREntity hr = this.getHR(request);
        if (hr == null) {
            this.errorDirect_404();
        }
        List<PostedRecumeBO> abandonResumeBOList = resumeService.getAbandonResumeByHrId(hr.getHrId());
        Map output = new TreeMap();
        output.put("NewResumeList",abandonResumeBOList);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
    }

    /**
     *   查看简历具体信息
     *   @author  PocketKnife
     *   @create  22:09 2020/5/9
    */
    @PostMapping(value = "/hr/resume/ResumeDesc")
    @ResponseBody
    public String getResumeDesc(HttpServletRequest request){
        int applicationId = (int) request.getSession().getAttribute("applicationId");

        ApplicationResumeHRBO applicationResumeHRBO = applicationService.getResumeHRBO(applicationId);
        UserEntity userEntity = userService.getUser(applicationResumeHRBO.getUserId());
        ResumeEntity resumeEntity = resumeService.getResumeById(applicationResumeHRBO.getResumeId());

        Map output = new TreeMap();
        output.put("userEntity",userEntity);
        output.put("resumeEntity",resumeEntity);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
    }
}
