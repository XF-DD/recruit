package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;
import qdu.java.recruit.entity.PositionEntity;
import qdu.java.recruit.pojo.PositionCategoryHRBO;
import qdu.java.recruit.pojo.PositionCompanyBO;

import java.util.ArrayList;
import java.util.List;

public interface PositionMapper {

    @Select("select positionId from position where title = #{title} and hrIdPub = #{hrId}")
    List<Integer> listPosIdByTitle(@Param("title") String title,@Param("hrId") int hrId);
    //===========以上为5/16新增 陈淯=========


    @Select("select * from position where statePub = 1")
    ArrayList<PositionEntity> listPosAll();

    @Select("select * from position where positionId = #{posId} and statePub = 1")
    PositionEntity getPosition(@Param("posId") int posId);

    @Select("select p.*,c.* from position p , category c where p.hrIdPub = #{hrId} and p.statePub = 1 and p.categoryId = c.categoryId order by p.releaseDate DESC")
    ArrayList<PositionCategoryHRBO> listHRPosWithCag(@Param("hrId") int hrId);

    @Select("select * from position  where hrIdPub = #{hrId} and statePub = 1 order by releaseDate DESC")
    ArrayList<PositionEntity> listHRPos(@Param("hrId") int hrId);

    @Select("select p.*,c.* from position p,department d,company c \n" +
            "where p.departmentId = d.departmentId and d.companyId = c.companyId \n" +
            "and title like #{keyword} and statePub = 1 \n" +
            "order by ${order} DESC")
    ArrayList<PositionCompanyBO> listSearchPos(@Param("keyword") String keyword,@Param("order") String order);

    @Select("select p.*,c.* from position p,department d,company c\n" +
            "where p.departmentId = d.departmentId and d.companyId = c.companyId \n" +
            "and categoryId = #{categoryId} and statePub = 1 \n" +
            "order by releaseDate DESC")
    ArrayList<PositionCompanyBO> listCategoryPos(@Param("categoryId") int categoryId);

    @Select("select count(*) from position where categoryId = #{categoryId}")
    int countCategoryPos(@Param("categoryId") int categoryId);

    @Update("update position set hits = hits+1 where positionId = #{positionId}")
    int updateHits(@Param("positionId") int positionId);

    @Select("select p.*,c.* from position p,department d,company c \n" +
            "where p.departmentId = d.departmentId and d.companyId = c.companyId and p.positionId = #{posId} limit 1")
    PositionCompanyBO listPosCompany(@Param("posId") int posId);

    @Select("select count(*) from position where hrIdPub=#{hrIdPub}")
    int countHRPos(@Param("hrIdPub") int hrIdPub);

    @Delete("delete from position where positionId = #{posId}")
    int delete(@Param("posId") int posId);

    @Update("update position set title = #{title},requirement=#{requirement},quantity=#{quantity}," +
            "workCity=#{workCity},salaryUp=#{salaryUp},salaryDown=#{salaryDown}," +
            "validDate=#{validDate},statePub=#{statePub}" +
            " where positionId = #{posId}")
    int updatePosition(PositionEntity positionEntity);

    @Update("update position set statePub= #{statePub} where positionId = #{posId}")
    int updatePositionState(@RequestParam("statePub") int statePub, @RequestParam("posId") int posId);

    @Insert("insert into position (title,requirement,quantity,workCity,salaryUp,salaryDown,releaseDate,validDate,statePub," +
            "departmentId,categoryId,hrIdPub) " +
            "values(#{title},#{requirement},#{quantity},#{workCity},#{salaryUp},#{salaryDown},#{releaseDate},#{validDate},#{statePub}," +
            "#{departmentId},#{categoryId},#{hrIdPub})")
    int savePosition(PositionEntity positionEntity);

    @Select("select distinct title from position where hrIdPub =#{hrId}")
    List<String> listTitle(@RequestParam("hrId") int hrId);

}

