package qdu.java.recruit.controller.hr;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import qdu.java.recruit.controller.BaseController;
import qdu.java.recruit.entity.HREntity;
import qdu.java.recruit.entity.PositionEntity;
import qdu.java.recruit.entity.ResumeEntity;
import qdu.java.recruit.entity.UserEntity;
import qdu.java.recruit.pojo.ApplicationResumeHRBO;
import qdu.java.recruit.pojo.FavorPositionBO;
import qdu.java.recruit.service.FavorService;
import qdu.java.recruit.service.PositionService;
import qdu.java.recruit.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;



@RestController
public class ResumeController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    FavorService favorService;
    @Autowired
    PositionService positionService;

    @PostMapping("/hr/applyInfo")
    public String ResumeInfo(HttpServletRequest request) {
        HREntity hr = this.getHR(request);
        if (hr == null) {
            return errorDirect_404();
        }
        return null;
    }

    /**
     * 查看新简历
     * 根据HRId查询投递到该hr下的各个岗位的投递者的信息。
     *
     * @author PocketKnife
     * @create 12:12 2020/5/9
     */
    @PostMapping(value = "/hr/resume/{id}")
    @ResponseBody
    public String getResume(HttpServletRequest request) {
        class Resume {
            UserEntity user = null;
            PositionEntity positionEntity = null;

            Resume(UserEntity user, PositionEntity position) {
                this.user = user;
                this.positionEntity = position;
            }
        }

        HREntity hr = this.getHR(request);
        List<PositionEntity> positionList = null;
        List<Resume> resume = new ArrayList<Resume>();

        positionList = positionService.listPositionByHr(hr.getHrId());
        for (PositionEntity position : positionList) {
            if (!favorService.favorOrNotByPosId(position.getPositionId())) continue;
            List<FavorPositionBO> favorPositionBo = favorService.listFavorByPositionId(position.getPositionId());
            for (FavorPositionBO favor : favorPositionBo) {
                UserEntity user = userService.getUser(favor.getUserId());
                resume.add(new Resume(user, position));
            }
        }
        Map output = new TreeMap();
        output.put("resume", resume);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }


    /**
     * 查看简历具体信息
     *
     * @author PocketKnife
     * @create 22:09 2020/5/9
     */
    @PostMapping(value = "/hr/resume/ResumeDesc")
    @ResponseBody
    public String getResumeDesc(HttpServletRequest request, @RequestParam(value = "applicationId", defaultValue = "") int applicationId) {
        HREntity hr = this.getHR(request);
        if (hr == null) {
            return errorDirect_404();
        }
        ApplicationResumeHRBO applicationResumeHRBO = applicationService.getResumeHRBO(applicationId);
        UserEntity userEntity = userService.getUser(applicationResumeHRBO.getUserId());
        ResumeEntity resumeEntity = resumeService.getResumeById(applicationResumeHRBO.getResumeId());
        applicationService.updateResumeState(1, applicationId);
    }
}
