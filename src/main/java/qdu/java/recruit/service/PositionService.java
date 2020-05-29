package qdu.java.recruit.service;

import com.github.pagehelper.PageInfo;
import qdu.java.recruit.entity.PositionEntity;
import qdu.java.recruit.entity.UserEntity;
import qdu.java.recruit.pojo.PositionCategoryHRBO;
import qdu.java.recruit.pojo.PositionCompanyBO;

import java.util.List;


public interface PositionService {
    /**
     * 查询hr发布的职位
     */
    public List<String> listTitle(int hrId);

    /**
     * 按照title查询职位Id
     */
    public List<Integer> listPositionIdByTitle(String title,int hrId);

    //=============以上5/16新增 陈淯===============

    /**
     * 分页推荐职位
     *
     * @param user
     * @return
     */
    List<PositionCompanyBO> recPosition(UserEntity user, int page, int limit);

    /**
     *
     * @param keyword
     * @param orderBy
     * @param workCity
     * @param salaryDown
     * @param salaryUp
     * @param page
     * @param limit
     * @return
     */
    PageInfo<PositionCompanyBO> searchPosition(String keyword,
                                               String orderBy,
                                               String workCity,
                                               String salaryDown,
                                               String salaryUp,
                                               String companyProperty,
                                               int companyScale,
                                               String companyIndustry,
                                               int page,
                                               int limit);
    /**
     * 各分类职位索引页
     *
     * @param categoryId
     * @param page
     * @param limit
     * @return
     */
    PageInfo<PositionCompanyBO> listPosition(int categoryId, int page, int limit);

    /**
     * 根据职位Id查找返回职位
     *
     * @param positionId
     * @return
     */
    PositionEntity getPositionById(int positionId);

    /**
     * 根据hrid查询返回职位包含分类信息
     *
     * @param hrid
     * @return
     */
    PageInfo<PositionCategoryHRBO> listPositionByHrWithCag(int hrid, int page, int limit);

    List<PositionCategoryHRBO> listPositionByHrWithCag(int hrid);



    List<PositionEntity> listPositionByHr(int hrid);

    /**
     * 点击量+1
     *
     * @param positionId
     * @return
     */
    boolean updateHits(int positionId);

    /**
     * delete
     *
     * @param positionId
     */
    int deletePosition(int positionId);

    /**
     * update
     */
    int updatePosition(PositionEntity positionEntity);

    int updatePositionState(int statePub, int posId);

    int savePosition(PositionEntity positionEntity);

}
