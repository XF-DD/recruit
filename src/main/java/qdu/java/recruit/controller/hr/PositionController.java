package qdu.java.recruit.controller.hr;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import qdu.java.recruit.constant.GlobalConst;
import qdu.java.recruit.controller.BaseController;
import qdu.java.recruit.entity.*;
import qdu.java.recruit.pojo.PositionCategoryHRBO;
import qdu.java.recruit.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@Api(value = "职位管理",description = "职位管理")
public class PositionController extends BaseController {
    /**
     * postion part
     * private int positionId;
     private String title; *
     private String requirement; *
     private int quantity; *
     private String workCity; *
     private int salaryUp; *
     private int salaryDown; *
     private Date releaseDate;
     private Date validDate;
     private int statePub; *
     private int hits;
     private int categoryId;
     private int departmentId;
     private int hrIdPub;
     */

    @Autowired
    PositionService positionService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CommentService commentService;

    @Autowired
    CompanyService companyService;


    /**
     * 职位管理界面，点击职位，跳转到简历界面 5/16新增
     * 按照Id查询
     */
    @GetMapping("/hr/positionId/{positionId}")
    @ResponseBody
    public int jumpToResumePage(HttpSession session,
                                @PathVariable int positionId){

        PositionEntity positionById = positionService.getPositionById(positionId);
        if (positionById!=null){
            List<Integer> positionIds = Arrays.asList(positionId);
            session.setAttribute("positionId",positionIds);
            Map output = new TreeMap();
            output.put("positionId",positionIds);
            return 1;
        }else {
            session.setAttribute("positionId",null);
            return 0;
        }
    }

    /**
     * 下拉框点击职位名称，返回职位Id列表  5/16新增
     * 按照职位名称查询职位Id列表
     *
     * 再次回到无选中（全部）状态，title为空或者乱写
     */
    @GetMapping("/hr/position/category")
    @ResponseBody
    public int jumpToResumePage(HttpSession session,
                                HttpServletRequest request,
                                @RequestParam("title") String title){
        HREntity hr = this.getHR(request);
        List<Integer> positionIds = positionService.listPositionIdByTitle(title, hr.getHrId());
        if (positionIds.size()!=0){
            session.setAttribute("positionId",positionIds);
            Map output = new TreeMap();
            output.put("positionId",positionIds);
            return 1;
        }else {
            session.setAttribute("positionId",null);
            return 0;
        }

    }
//===============以上新增5/16 陈淯====================

    /**
     * 职位信息表
     * @param request
     * @return
     */
    @GetMapping("/hr/position/{page}")
    @ResponseBody
    public String showPostionInfo(HttpServletRequest request,
                                  @PathVariable int page,
                                  @RequestParam(value = "limit", defaultValue = "12") int limit){

        HREntity hr = this.getHR(request);

        if (hr == null) {
            this.errorDirect_404();
            //其实应该返回的是401，或者403
        }
        int id = hr.getHrId();
        page = page < 1 || page > GlobalConst.MAX_PAGE ? 1 : page;

        PageInfo<PositionCategoryHRBO> positionEntities = positionService.listPositionByHrWithCag(id,page,limit);

        Map output = new TreeMap();
        output.put("title", ("第" + page + "页"));
        output.put("hr", hr);
        output.put("positions",positionEntities);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }


    /**
     * 职位详情
     * @param request
     * @param id
     * @return
     */
    @PostMapping(value = "/hr/position/{id}")
    @ResponseBody
    public String getPosition(HttpServletRequest request, @PathVariable int id) {

        PositionEntity position = valide(request,id);

        //所属部门信息
        DepartmentEntity department = departmentService.getDepartment(position.getDepartmentId());
        //所属公司信息
        CompanyEntity company = companyService.getCompany(department.getCompanyId());
        //职位所属分类信息
        CategoryEntity category = categoryService.getCategory(position.getCategoryId());
        //分页评论信息

        if (!positionService.updateHits(id)) {
            this.errorDirect_404();
        }

        Map output = new TreeMap();
        output.put("position", position);
        output.put("department", department);
        output.put("company", company);
        output.put("category", category);

        JSONObject jsonObject = JSONObject.fromObject(output);

        return jsonObject.toString();
    }


    @PostMapping("/position{id}/delete")
    public int deletePosition(HttpServletRequest request,@PathVariable int id) {
        valide(request,id);
        return positionService.deletePosition(id);

    }

    /**
     * private String title; *
     private String requirement; *
     private int quantity; *
     private String workCity; *
     private int salaryUp; *
     private int salaryDown; *
     private Date validDate;
     * @param request
     * @param id
     * @return
     */
    @PostMapping("/position{id}/update")
    public int updatePosition(HttpServletRequest request,
                              @PathVariable int id,
                              @RequestParam String title,
                              @RequestParam String requirement,
                              @RequestParam int quantity,
                              @RequestParam String workCity,
                              @RequestParam int salaryDown,
                              @RequestParam Date validDate
    ) {
        PositionEntity positionEntity = valide(request,id);
        positionEntity.setTitle(title);
        positionEntity.setRequirement(requirement);
        positionEntity.setQuantity(quantity);
        positionEntity.setValidDate(validDate);
        positionEntity.setSalaryDown(salaryDown);
        positionEntity.setWorkCity(workCity);

        return positionService.updatePosition(positionEntity);
    }

    /**
     *职位下架,即不会在出现
     * @param request
     * @param id
     * @return
     */
    @PostMapping("/position{id}/withdraw")
    public int withdrawPosition(HttpServletRequest request,
                                @PathVariable int id) {
        PositionEntity positionEntity = valide(request,id);
        positionEntity.setStatePub(0);//0为下架
        return positionService.updatePosition(positionEntity);
    }


    /**
     * 职位创建
     */
    @PostMapping("hr{id}/position/create")
    public int createPosition(ModelMap modelMap, HttpServletRequest request, @PathVariable int id, PositionEntity positionEntity) {
        HREntity hr = this.getHR(request);
        List<CategoryEntity> categoryEntities = categoryService.getAll();
        if(hr == null) {
            this.errorDirect_404();
        }
        id = hr.getHrId();
        modelMap.put("categoryEntities",categoryEntities);
        positionEntity.setReleaseDate(new Date());
        positionEntity.setStatePub(1);
        positionEntity.setHrIdPub(id);
        return positionService.savePosition(positionEntity);


    }

    /**
     * 权限验证
     * @param request
     * @param id
     * @return
     */
    public PositionEntity valide(HttpServletRequest request, int id) {
        HREntity hr = this.getHR(request);
        PositionEntity position = positionService.getPositionById(id);
        if(hr == null || position == null) {
            this.errorDirect_404();
            return null;
        }
        else {
            int hrid = hr.getHrId();
            if(position.getHrIdPub() != hrid) {
                this.errorDirect_404();
                return null;
            }
            return position;
        }

    }

}
