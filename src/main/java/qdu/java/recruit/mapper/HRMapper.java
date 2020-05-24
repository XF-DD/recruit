package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.*;
import qdu.java.recruit.entity.HREntity;
import qdu.java.recruit.pojo.PostedRecumeBO;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * private int hrId;
 * private String hrMobile;
 * private String hrPassword;
 * private String hrName;
 * private String hrEmail;
 * private String description;
 * private int departmentId;
 * </P>
 */
public interface HRMapper {
    /**
     * <p>`hrId` int(11) NOT NULL AUTO_INCREMENT,
     * `hrMobile` varchar(11) NOT NULL,
     * `hrPassword` varchar(500) NOT NULL,
     * `hrName` varchar(50) DEFAULT NULL,
     * `hrEmail` varchar(50) DEFAULT NULL,
     * `description` longtext,
     * `departmentId` int(11) NOT NULL,</p>
     */

    @Select("select * from hr")
    ArrayList<HREntity> listHR();

    @Select("select * from hr where hrId = #{hrId}")
    HREntity getHR(@Param("hrId") int hrId);

    @Select("select COUNT(*) from hr")
    int getHRSize();

    @Update({"update hr set hrPassword = #{hrPassword},hrName=#{hrName},hrEmail=#{hrEmail}," +
            "description=#{description},departmentId=#{departmentId} where hrId = #{hrId}"})
    int updateHR(HREntity hrEntity);

    @Insert({"insert into hr(hrMobile,hrPassword,hrName,hrEmail,description,departmentId) " +
            "values(#{hrMobile},#{hrPassword},#{hrName},#{hrEmail},#{description},#{departmentId})"})
    int saveHR(HREntity hrEntity);

    @Select("select * from hr where hrMobile = #{hrMobile} limit 1")
    HREntity getHRByMobile(@Param("hrMobile") String hrMobile);

    /*
     *黄少龙
     * hr展示功能
     * 5/23
     */
    @Select("select * from hr where hrId!=#{hrId} and companyId=#{companyId}")
    List<HREntity> searchHr(@Param("hrId") int hrId,@Param("companyId") int companyId);
    /*
     *黄少龙
     * hr删除
     * 5/23
     */
    @Delete("delete from hr where hrId = #{hrid}")
    int deleteHR(@Param("hrid") int hrid);
}
