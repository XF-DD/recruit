package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import qdu.java.recruit.entity.ResumeEntity;
import qdu.java.recruit.entity.UserEntity;
import qdu.java.recruit.pojo.PostedRecumeBO;

import java.util.ArrayList;
import java.util.List;

public interface ResumeMapper {

    @Select("select resumeId from resume where userId = #{userId} limit 1")
    int getResumeId(@Param("userId") int userId);

    @Select("select * from resume where userId = #{userId} limit 1")
    ResumeEntity getResumeById(@Param("userId") int userId);

    @Update("update resume set ability = #{ability},internship=#{internship},workExperience=#{workExperience}," +
            "certificate = #{certificate},jobDesire = #{jobDesire} where userId = #{userId}")
    int saveResume(ResumeEntity resumeEntity);

    @Insert("insert into resume(ability,internship,workExperience,certificate,jobDesire,userId) " +
            "values (#{ability},#{internship},#{workExperience},#{certificate},#{jobDesire},#{userId})")
    int createResume(ResumeEntity resumeEntity);


    @Select("select a.applicationId,u.*,p.title \n" +
            "from user u join application a on u.userId = a.userId \n" +
            "join position  p on p.positionId = a.positionId \n" +
            "where hrId = #{hrId} and state = #{state} order by a.recentTime DESC")
   List<PostedRecumeBO> getResumeByState(@Param("hrId") int hrId ,@Param("state") int state);


    //查看所有简历
    @Select("select a.applicationId,u.*,p.title \n" +
            "from user u join application a on u.userId = a.userId \n" +
            "join position  p on p.positionId = a.positionId \n" +
            "where hrId = #{hrId} order by a.recentTime DESC")
    List<PostedRecumeBO> getAllResume(@Param("hrId") int hrId);


    @Select("select a.applicationId,u.*,p.title \n"+
            "from user u join application a on u.userId = a.userId \n"+
            "join position  p on p.positionId = a.positionId \n"+
            "where hrId = #{hrId} and state >=2 \n" +
            "order by a.recentTime DESC")
    List<PostedRecumeBO> getInterviewResume(@Param("hrId")int hrId);


    //将获得offer的学生，state设置为-3
    @Update("update application set state = #{state}" +
            " where applicationId = #{applicationId}")
    int setState(@Param("state") int state, @Param("applicationId") int applicationId);


    //将已发送offer的学生，其余职位申请设置为未通过
    @Update("update application set state = #{state}" +
            " where  hrId = #{hrId} and userId=#{userId} and state != -3")
    int setState1(@Param("state") int state, @Param("hrId") int hrId, @Param("applicationId") int applicationId, @Param("userId") int userId);

    //新增未测试，搜索用户
    @Select("select b.applicationId,a.* from user as a, application as b where a.userId = b.userId and b.hrId=#{hrId} and (a.mobile like #{keyword} or a.name like #{keyword})")
    ArrayList<UserEntity> searchUser(@Param("hrId") int hrId, @Param("keyword") String keyword);

    //发送面试信息
    @Insert("insert into message(state,news,hrId,userId) " +
            "values (#{state},#{news},#{hrId},(select userId from application where applicationId=#{applicationId}))")
    int sendOfferNews(@Param("state") int state, @Param("applicationId") int applicationId, @Param("news") String news, @Param("hrId") int hrId);

    //通过applicationId找到userId
    @Select("select userId from application where applicationId = #{applicationId}")
    int getUserId(@Param("applicationId")int applicationId);

}
