package qdu.java.recruit.controller.hr;

import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    /**
     *   查询简历
     *   通过state判断查询的简历状态，返回相应状态的简历
     *   可查询新、备选、放弃、未通过、通过、一面、二面简历
     *   @author  PocketKnife
     *   @create  23:34 2020/5/9
    */
    @GetMapping(value = "/hr/resume/{state}")
    @ResponseBody
    public String getResume(HttpServletRequest request , @PathVariable int state){
        HREntity hr = this.getHR(request);
        if(hr == null) {
            return errorDirect_404();
        }
        List<PostedRecumeBO> resumes = resumeService.getResumeByState(hr.getHrId(),state);
        Map output = new TreeMap();
        output.put("resumes",resumes);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }

    /**
     *   查看所有简历
     *   @author  PocketKnife 
     *   @create  23:40 2020/5/9
    */
    @ResponseBody
    @GetMapping("/hr/resumeInfo")
    public String ResumeInfo(HttpServletRequest request) {
        HREntity hr = this.getHR(request);
        if(hr == null) {
            return errorDirect_404();
        }
        List<PostedRecumeBO>  resumeInfo= resumeService.getAllResume(hr.getHrId());
        Map output = new TreeMap();
        output.put("resumeInfo",resumeInfo);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }

    /**
     *   查看正在安排面试的简历
     *   根据hrId查询正在面试中的简历(包括一面，二面...)
     *   @author  PocketKnife
     *   @create  19:11 2020/5/9
    */
    @GetMapping(value = "/hr/resume/interviewresume")
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
     *   查看简历具体信息
     *   @author  PocketKnife
     *   @create  22:09 2020/5/9
    */
    @PostMapping(value = "/hr/resume/ResumeDesc")
    @ResponseBody
    public String getResumeDesc(HttpServletRequest request){
        HREntity hr = this.getHR(request);
        if(hr == null) {
            return errorDirect_404();
        }
        int applicationId = (int) request.getSession().getAttribute("applicationId");

        ApplicationResumeHRBO applicationResumeHRBO = applicationService.getResumeHRBO(applicationId);
        UserEntity userEntity = userService.getUser(applicationResumeHRBO.getUserId());
        ResumeEntity resumeEntity = resumeService.getResumeById(applicationResumeHRBO.getResumeId());
        applicationService.updateResumeState(1,applicationId);

        Map output = new TreeMap();
        output.put("userEntity",userEntity);
        output.put("resumeEntity",resumeEntity);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
    }
}
