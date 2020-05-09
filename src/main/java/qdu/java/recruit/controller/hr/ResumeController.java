package qdu.java.recruit.controller.hr;

import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qdu.java.recruit.controller.BaseController;
import qdu.java.recruit.entity.HREntity;
import qdu.java.recruit.pojo.UserPositionHRBO;
import qdu.java.recruit.pojo.UserResumeHRBO;
import qdu.java.recruit.service.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@RestController
@Api(value = "Resume",description = "Resume接口")
public class ResumeController extends BaseController{

    @Autowired
    ApplicationService applicationService;

    /**
     * 查看所有简历  简略信息----投递人信息，应聘岗位
     */
    @ResponseBody
    @GetMapping("/hr/resumeInfo")
    public String ResumeInfo(HttpServletRequest request) {
        HREntity hr = this.getHR(request);
        if(hr == null) {
            return errorDirect_404();
        }
        List<UserPositionHRBO> resumeAndUserInfoBOS = applicationService.selectAllResumeByHrId(hr.getHrId());
        Map output = new TreeMap();
        output.put("resumeAndUserInfo",resumeAndUserInfoBOS);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }

    /**
     * 查看新简历  简略信息----投递人信息，应聘岗位
     */
    @ResponseBody
    @GetMapping("/hr/newresumeInfo")
    public String NewResumeInfo(HttpServletRequest request) {
        HREntity hr = this.getHR(request);
        if(hr == null) {
            return errorDirect_404();
        }
        List<UserPositionHRBO> resumeAndUserInfoBOS = applicationService.newOrOldResume(hr.getHrId(),0);
        Map output = new TreeMap();
        output.put("newResume",resumeAndUserInfoBOS);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }
    /**
     * 查看备选简历（已看）  简略信息----投递人信息，应聘岗位
     */
    @ResponseBody
    @GetMapping("/hr/oldresumeInfo")
    public String OldResumeInfo(HttpServletRequest request) {
        HREntity hr = this.getHR(request);
        if(hr == null) {
            return errorDirect_404();
        }
        List<UserPositionHRBO> resumeAndUserInfoBOS = applicationService.newOrOldResume(hr.getHrId(),1);
        Map output = new TreeMap();
        output.put("oldResume",resumeAndUserInfoBOS);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }

    /**
     * 移除简历（条件不符合或者面试不通过）
     */
    @PutMapping("/hr/removeresume/{applicationId}")
    public String RemoveResume(HttpServletRequest request, @PathVariable int applicationId) {
        HREntity hr = this.getHR(request);
        if(hr == null) {
            return errorDirect_404();
        }
        if (applicationService.removeResume(applicationId)==0){
            return errorDirect_404();
        }
        return "查看简历界面";
    }

    /**
     * 安排面试    flag=1 代表 1面
     */
    @PutMapping("/hr/Interview/{applicationId}")
    public String arrangeInterview(HttpServletRequest request, @RequestParam int flag,@RequestParam String interviewsDesc,@PathVariable int applicationId ) {
        HREntity hr = this.getHR(request);
        String interviewDesc = interviewsDesc +"("+ flag + "面）";
        if(hr == null) {
            return errorDirect_404();
        }
        if (applicationService.arrangeInterview(interviewDesc,applicationId,(flag+1))==0){
            return errorDirect_404();
        }
        return "查看简历界面111";
    }

    /**
     * 查看简历具体信息（包括用户信息、简历信息）
     */
    @ResponseBody
    @GetMapping("hr/checkresume/{applicationId}")
    public String checkResumeByAId(HttpServletRequest request, @PathVariable int applicationId){
        HREntity hr = this.getHR(request);
        if(hr == null) {
            return errorDirect_404();
        }
        UserResumeHRBO userResumeHRBO = applicationService.checkResumeByAId(applicationId);
        applicationService.updateResumeState(1,applicationId);
        Map output = new TreeMap();
        output.put("userResume",userResumeHRBO);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }




}
