package qdu.java.recruit.controller.hr;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import qdu.java.recruit.constant.GlobalConst;
import qdu.java.recruit.controller.BaseController;
import qdu.java.recruit.entity.CompanyEntity;
import qdu.java.recruit.entity.DepartmentEntity;
import qdu.java.recruit.entity.HREntity;
import qdu.java.recruit.pojo.ApplicationPositionHRBO;
import qdu.java.recruit.pojo.PositionCategoryHRBO;
import qdu.java.recruit.pojo.PostedRecumeBO;
import qdu.java.recruit.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p>
 * private int hrId;
 * private String hrMobile;
 * private String hrPassword;
 * private String hrName;
 * private String hrEmail;
 * private String description;
 * private int departmentId;
 * </p>
 */
@RestController
@Api(value = "HR接口", description = "HR接口")
public class HRController extends BaseController {

    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    HRService hrService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    PositionService positionService;

    @Autowired
    CompanyService companyService;

    @Autowired
    DepartmentService departmentService;

    /**
     * 用户注册返回 0 -> 失败 1 -> 成功
     */
    @PostMapping("/hr/register/first")
    @ResponseBody
    //传入部门，公司id
    public String checkCompanyCode(ModelMap map,
                                   @RequestParam String CompanyCode,
                                   HttpServletRequest request,
                                   DepartmentEntity departmentEntity) {
        CompanyEntity companyEntity = companyService.getCompany(CompanyCode);
        if (companyEntity == null) {
            throw new RuntimeException("公司不存在");
        } else {
            List<DepartmentEntity> departmentEntities = departmentService.getDepartmentByCompany(
                    companyEntity.getCompanyId());
            map.put("departments", departmentEntities);
            request.setAttribute("department", departmentEntity.getDepartmentId());
            return hrDirect("hr/register/second");
        }
    }


    @PostMapping(value = "hr/register/second")
    @ResponseBody
    public int userRegister(@RequestParam HREntity user,
                            HttpServletRequest request) {

        int deparmentId = (int) request.getAttribute("department");
        user.setDepartmentId(deparmentId);
        String password = user.getHrPassword();

        //验证mobile 和 password是否为空
        if (user.getHrMobile() == null || user.getHrPassword() == null) {
            return 0;
        }
        if (hrService.registerHR(user)) {
            return 1;
        }
        return 1;
    }


    /**
     * hr登录
     * 5/23
     * 黄少龙
     * -1-陆失败，0-普通hr，1-roothr
     *
     * @param httpSession
     * @return
     */
    @PostMapping(value = "/hr/loginPost")
    public int userLogin(HttpSession httpSession,
                         @RequestParam String hrName,
                         @RequestParam String hrPass) {

        String mobile = hrName;
        String password = hrPass;
        if (hrName == null || hrPass == null) {
            return -1;
        }

        if (hrService.loginHR(mobile, password)) {
            System.out.println("匹配到了");
            HREntity hrEntity = hrService.getHRByMobile(mobile);
            httpSession.setAttribute("hr", hrEntity);
            return hrEntity.getPower();
        }
        return -1;
    }


    /**
     * HR个人信息 输出
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/hr/info")
    @ResponseBody
    public String showInfo(HttpServletRequest request) {

        //用户个人信息
//        UserEntity user = this.getUser(request);
        HREntity hr = this.getHR(request);
//        int id = hr.getHrId();
//        HREntity user = hrService.getHR(5);
        if (hr == null) {
            this.errorDirect_404();
            //其实应该返回的是401，或者403
        }
        int id = hr.getHrId();

        //收件箱
        List<ApplicationPositionHRBO> applyPosList = applicationService.listApplyInfoByHr(id);
        //创建的职位
        List<PositionCategoryHRBO> positionEntities = positionService.listPositionByHrWithCag(id);

        Map output = new TreeMap();
        output.put("hr", hr);
        output.put("applyPosList", applyPosList);
        output.put("positions", positionEntities);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }

    /**
     * 黄少龙
     * 5/23
     * 个人信息更新 功能
     *
     * @param request
     * @param password
     * @param name
     * @param mobile
     * @param email
     * @param description
     * @param departmentId
     * @return
     */
    @PostMapping("/hr/info/update/{hrid}")
    public String updateInfo(HttpSession httpSession,
                             HttpServletRequest request,
                             @PathVariable int hrid,
                             @RequestParam("hrMobile") String mobile,
                             @RequestParam("hrPassword") String password,
                             @RequestParam("hrName") String name,
                             @RequestParam("hrEmail") String email,
                             @RequestParam("description") String description,
                             @RequestParam("departmentId") int departmentId) {

        int hrId = this.getHRId(request);
        request.getSession().getAttribute("hr");

        HREntity HREntity = new HREntity();
        HREntity.setHrId(hrid);
        HREntity.setHrPassword(password);
        HREntity.setHrName(name);
        HREntity.setHrEmail(email);
        HREntity.setDescription(description);
        HREntity.setDepartmentId(departmentId);

        if (!hrService.updateHR(HREntity)) {
            this.errorDirect_404();
        } else {
            if (hrService.loginHR(mobile, password) && hrId == hrid) {//仅当hr更新自己的消息时才重新设置session
                System.out.println("匹配到了");
                httpSession.setAttribute("hr", hrService.getHRByMobile(mobile));
            }
        }
        return this.hrDirect("hr_info");
    }

    /**
     * root
     * 子hr搜索功能
     * 黄少龙
     * 查询所有的简历按keyword
     * 5/19
     */
    @PostMapping("/roothr/search")
    @ResponseBody
    public String ResumeSearch(HttpServletRequest request,
                               @RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "limit", defaultValue = "6") int limit) {
        HREntity hr = this.getHR(request);
        if (hr == null) {
            return errorDirect_404();
        }

        page = page < 1 || page > GlobalConst.MAX_PAGE ? 1 : page;
        PageInfo<HREntity> posInfo = hrService.searchHr(hr.getHrId(), hr.getCompanyId(), page, limit);

        Map output = new TreeMap();
        output.put("hr", hr);
        output.put("title", ("第" + page + "页"));
        output.put("posInfo", posInfo);
        JSONObject jsonObject = JSONObject.fromObject(output);
        return jsonObject.toString();
    }

    /**
     * root
     * 删除子hr
     * 黄少龙
     * 5/23
     */
    @PostMapping("/roothr/delete/{hrid}")
    @ResponseBody
    public String deleteHr(HttpServletRequest request, @PathVariable int hrid
    ) {
        HREntity hr = this.getHR(request);
        if (hr == null) {
            return errorDirect_404();
        }

        if (hrService.deleteHR(hrid)) {

            return "删除成功";
        }else

        return "删除失败";
    }

    /**
     * 用户注销 功能
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/logout")
    public String userLogout(HttpServletRequest request) {
        // 清除session
        Enumeration<String> em = request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        request.getSession().removeAttribute(GlobalConst.LOGIN_SESSION_KEY_HR);
        request.getSession().invalidate();

        return userDirect("logout_success");
    }
}
