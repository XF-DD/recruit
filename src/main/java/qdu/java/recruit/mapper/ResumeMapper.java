package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import qdu.java.recruit.entity.ResumeEntity;
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
            "where hrId = #{hrId} and state = 0 order by a.recentTime DESC")
    ArrayList<PostedRecumeBO> getNewResume(@Param("hrId")int hrId);

    @Select("select a.applicationId,u.*,p.title \n"+
            "from user u join application a on u.userId = a.userId \n"+
            "join position  p on p.positionId = a.positionId \n"+
            "where hrId = #{hrId} and state = 1 \n" +
            "order by a.recentTime DESC")
    List<PostedRecumeBO> getSeenResume(@Param("hrId")int hrId);

    @Select("select a.applicationId,u.*,p.title \n"+
            "from user u join application a on u.userId = a.userId \n"+
            "join position  p on p.positionId = a.positionId \n"+
            "where hrId = #{hrId} and NOT IN(1,0,-1,-2) \n" +
            "order by a.recentTime DESC")
    List<PostedRecumeBO> getInterviewResume(@Param("hrId")int hrId);

    @Select("select a.applicationId,u.*,p.title \n"+
            "from user u join application a on u.userId = a.userId \n"+
            "join position  p on p.positionId = a.positionId \n"+
            "where hrId = #{hrId} and state = -1 \n" +
            "order by a.recentTime DESC")
    List<PostedRecumeBO> getFailedResume(@Param("hrId")int hrId);

    @Select("select a.applicationId,u.*,p.title \n"+
            "from user u join application a on u.userId = a.userId \n"+
            "join position  p on p.positionId = a.positionId \n"+
            "where hrId = #{hrId} and state = -2 \n" +
            "order by a.recentTime DESC")
    List<PostedRecumeBO> getAbandonResume(@Param("hrId")int hrId);
}
