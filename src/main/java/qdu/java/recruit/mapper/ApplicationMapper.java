package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import qdu.java.recruit.entity.ApplicationEntity;
import qdu.java.recruit.pojo.ApplicationPositionHRBO;
import qdu.java.recruit.pojo.UserPositionHRBO;
import qdu.java.recruit.pojo.UserResumeHRBO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public interface ApplicationMapper {

    @Select("select * from application where resumeId = #{resumeId}")
    ArrayList<ApplicationEntity> listApplication(@Param("resumeId") int resumeId);

    @Select("select * from application where resumeId = #{resumeId} and positionId = #{posId} limit 1")
    ApplicationEntity getApplication(@Param("resumeId") int resumeId, @Param("posId") int posId);

    @Insert("insert into application(state,recentTime,resumeId,positionId,hrId,userId) values (0,#{recentTime},#{resumeId},#{positionId},#{hrId},#{userId})")
    int saveApplication(@Param("recentTime") Timestamp recentTime, @Param("resumeId") int resumeId, @Param("positionId") int positionId,@Param("hrId") int hrId,@Param("userId") int userId);


    //查看所有简历
    @Select("select u.*,p.title,a.applicationId from user u,position p ,application a where a.userId = u.userId and a.positionId = p.positionId and a.hrId = #{hrId} ORDER BY a.recentTime")
    List<UserPositionHRBO> selectAllResumeByHrId(@Param("hrId") int hrId);

    //查看 新/旧 简历
    @Select("select u.*,p.title,a.applicationId from user u,position p ,application a where a.userId = u.userId and a.positionId = p.positionId and a.hrId = #{hrId} and state=#{flag} ORDER BY a.recentTime")
    List<UserPositionHRBO> newOrOldResume(@Param("hrId") int hrId , @Param("flag") int flag);

    //更新简历状态  0未看  1已看
    @Update("update application set state = #{flag} where applicationId = #{applicationId}")
    int updateResume(@Param("flag") int flag,@Param("applicationId") int applicationId);

    //安排面试，输入(String时间地点)
    @Update("UPDATE application SET state=#{flag},interviewsDesc = #{interviewsDesc} WHERE applicationId = #{applicationId} AND state!=-1")
    int OperateInterviews(@Param("flag")int flag , @Param("interviewsDesc") String interviewsDesc,@Param("applicationId") int applicationId);

    //查看简历，具体内容
    @Select("Select r.*,u.* from resume r,user u ,application a where a.applicationId=#{applicationId} and a.userId = u.userId and a.resumeId=r.resumeId")
    UserResumeHRBO checkResume(@Param("applicationId") int applicationId);



    /**
     * 申请处理完成：查询返回 申请 职位 处理hr信息
     * @param resumeId
     * @return
     */
    @Select("select a.applicationId,a.state,a.recentTime,a.resumeId,p.*,h.hrId,h.hrMobile,h.hrName,h.hrEmail\n" +
            "from application a,position p,hr h\n" +
            "where a.positionId = p.positionId and a.hrId = h.hrId \n" +
            "and a.hrId is not null and a.resumeId = #{resumeId}\n" +
            "order by recentTime;")
    ArrayList<ApplicationPositionHRBO> listAppPosHR(@Param("resumeId") int resumeId);

    /**
     * 申请待处理：查询返回 申请 职位 发布hr信息
     * @param resumeId
     * @return
     */
    @Select("select a.applicationId,a.state,a.recentTime,a.resumeId,p.*,h.hrId,h.hrMobile,h.hrName,h.hrEmail\n" +
            "from application a,position p,hr h\n" +
            "where a.positionId = p.positionId and p.hrIdPub = h.hrId \n" +
            "and a.hrId is null and a.resumeId = #{resumeId}\n" +
            "order by p.releaseDate;")
    ArrayList<ApplicationPositionHRBO> listAppPosHRPub(@Param("resumeId") int resumeId);

}
