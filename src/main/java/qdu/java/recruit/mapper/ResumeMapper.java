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

    //需要修改
    @Update("update resume set ability = #{ability},internship=#{internship},workExperience=#{workExperience}," +
            "certificate = #{certificate},jobDesire = #{jobDesire} where userId = #{userId}")
    int saveResume(ResumeEntity resumeEntity);

    //需要修改
    @Insert("insert into resume(ability,internship,workExperience,certificate,jobDesire,userId) " +
            "values (#{ability},#{internship},#{workExperience},#{certificate},#{jobDesire},#{userId})")
    int createResume(ResumeEntity resumeEntity);


    @Select("select a.applicationId,u.*,p.title \n" +
            "from user u join application a on u.userId = a.userId \n" +
            "join position  p on p.positionId = a.positionId \n" +
            "where hrId = #{hrId} and state = #{state} order by a.recentTime DESC")
   List<PostedRecumeBO> getResumeByState(@Param("hrId") int hrId, @Param("state") int state);

    /**
     * 5/19陈淯
     * 查找大于三面的简历
     */
    @Select("select a.applicationId,u.*,p.title \n" +
            "from user u join application a on u.userId = a.userId \n" +
            "join position  p on p.positionId = a.positionId \n" +
            "where hrId = #{hrId} and state > 4 order by a.recentTime DESC")
   List<PostedRecumeBO> getResumeByStateGThree(@Param("hrId") int hrId);


    /**
     * 5/16  陈淯
     * 按照状态 + 职位ids  查找
<<<<<<< HEAD
=======
     *
>>>>>>> 0d4957c0cb80e85703cd755f85e14e2489a562b5
     * @param hrId
     * @return
     */
    @Select({
            "<script>",
            "select a.applicationId,u.*,p.title from user u join application a on u.userId = a.userId join position  p on p.positionId = a.positionId where a.positionId in ",
            "<foreach collection='positionIds' item='item' index='index' open='(' separator=',' close=')'>",
            "(#{item})",
            "</foreach>",
            "and hrId = #{hrId} and state = #{state} order by a.recentTime DESC",
            "</script>"
    })
    List<PostedRecumeBO> getResumeByStateWithPosIds(@Param("hrId") int hrId, @Param("state") int state,@Param("positionIds") List<Integer> positionIds);

    /**
     * 5/19  陈淯
     * 按照状态 + 职位ids  查找
     * 查找大于三面的简历
     * @param hrId
     * @return
     */
    @Select({
            "<script>",
            "select a.applicationId,u.*,p.title from user u join application a on u.userId = a.userId join position  p on p.positionId = a.positionId where a.positionId in ",
            "<foreach collection='positionIds' item='item' index='index' open='(' separator=',' close=')'>",
            "(#{item})",
            "</foreach>",
            "and hrId = #{hrId} and state >4 order by a.recentTime DESC",
            "</script>"
    })
    List<PostedRecumeBO> getResumeByStateGThreeWithPosIds(@Param("hrId") int hrId,@Param("positionIds") List<Integer> positionIds);


    /**
     * 5/16  陈淯
     * 按照状态 + 职位ids  查找
     * @param hrId
     * @return
     */
    @Select({
            "<script>",
            "select a.applicationId,u.*,p.title from user u join application a on u.userId = a.userId \n" +
            "join position p on p.positionId = a.positionId where a.positionId in ",
            "<foreach collection='positionIds' item='item' index='index' open='(' separator=',' close=')'>",
            "(#{item})",
            "</foreach>",
            "and hrId = #{hrId} order by a.recentTime DESC",
            "</script>"
    })
    List<PostedRecumeBO> getAllResumeWithPosIds(@Param("hrId") int hrId, @Param("positionIds") List<Integer> positionIds);

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
    List<PostedRecumeBO> getInterviewResume(@Param("hrId") int hrId);

    //通过applicationId找到userId
    @Select("select userId from application where applicationId = #{applicationId}")
    int getUserId(@Param("applicationId") int applicationId);


    //将获得offer的学生，state设置为-3
    @Update("update application set state = #{state}" +
            " where applicationId = #{applicationId}")
    int setState(@Param("state") int state, @Param("applicationId") int applicationId);

    //将已发送offer的学生，其余职位申请设置为未通过
    @Update("update application set state = #{state}" +
            " where  hrId = #{hrId} and userId=#{userId} and state != -3")
    int setState1(@Param("state") int state, @Param("hrId") int hrId, @Param("applicationId") int applicationId, @Param("userId") int userId);

    //搜索用户
    @Select("select b.applicationId,a.* from user as a, application as b where a.userId = b.userId and b.hrId=#{hrId} " +
            "and (a.mobile like #{keyword} or a.name like #{keyword}) order by b.recentTime DESC")
    ArrayList<PostedRecumeBO> searchUser(@Param("hrId") int hrId, @Param("keyword") String keyword);

    //发送面试信息
    @Insert("insert into message(state,news,hrId,userId,applicationId) " +
            "values (#{state},#{news},#{hrId},(select userId from application where applicationId=#{applicationId}),#{applicationId})")
    int sendOfferNews(@Param("state") int state, @Param("applicationId") int applicationId, @Param("news") String news, @Param("hrId") int hrId);


    @Select("select a.applicationId,u.*,p.title \n" +
            "from user u join application a on u.userId = a.userId \n" +
            "join position  p on p.positionId = a.positionId \n" +
            "where hrId = #{hrId} and state = #{state} and (u.mobile like #{keyword} or u.name like #{keyword}) " +
            "order by a.recentTime DESC")
    List<PostedRecumeBO> getResumeByTitleAndState(@Param("hrId") int hrId, @Param("state") int state, @Param("keyword") String keyword);

    @Select({
            "<script>",
            "select a.applicationId,u.*,p.title from user u join application a on u.userId = a.userId join position  p on p.positionId = a.positionId where a.positionId in ",
            "<foreach collection='positionIds' item='item' index='index' open='(' separator=',' close=')'>",
            "(#{item})",
            "</foreach>",
            "and hrId = #{hrId} and state = #{state} and (u.mobile like #{keyword} or u.name like #{keyword}) " +
                    "order by a.recentTime DESC",
            "</script>"
    })
    List<PostedRecumeBO> getResumeByTitleAndStateWithPosIds(@Param("hrId") int hrId, @Param("state") int state, @Param("positionIds") List<Integer> positionIds, @Param("keyword") String keyword);
}
