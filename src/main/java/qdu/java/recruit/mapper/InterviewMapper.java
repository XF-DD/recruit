package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import qdu.java.recruit.pojo.InterviewDescBO;

import java.util.List;

/**
 * @author ChenGuiHong
 * @create 2020-05-18  22:37
 */
public interface InterviewMapper {

    //查询所有轮次或者更多的所有面试信息（不同面试轮数，对应职位）。
    @Select("select m.news,a.state,m.msgSendTime,m.isCheck,a.applicationId,u.*,p.title \n"+
            "from user u join application a on u.userId = a.userId \n"+
            "join position  p on p.positionId = a.positionId \n"+
            "join message m on a.applicationId = m.applicationId \n"+
            "where a.hrId = #{hrId} and a.state >= #{state} \n"+
            "order by a.state DESC,m.msgSendTime DESC ")
    List<InterviewDescBO> getAllOrMoreInterviewDesc(@Param("hrId")int hrId,@Param("state")int state);

    //查询所有轮次或者更多的对应职位的所有面试信息（不同面试轮数，对应职位）。
    @Select({
            "<script>",
            "select m.news,a.state,m.msgSendTime,m.isCheck,a.applicationId,u.*,p.title from user u join application a on u.userId = a.userId join position  p on p.positionId = a.positionId join message m on a.applicationId = m.applicationId where a.hrId =#{hrId} and a.state >= #{state} and p.positionId in ",
            "<foreach collection='positionIds' item='item' index='index' open='(' separator=',' close=')'>",
            "(#{item})",
            "</foreach>",
            "order by a.state DESC,m.msgSendTime DESC",
            "</script>"})
    List<InterviewDescBO> getAllOrMoreInterviewDescByPosition(@Param("hrId")int hrId,@Param("state")int state,@Param("positionIds") List<Integer> positionIds);

    @Select({"<script>",
            "select m.news,a.state,m.msgSendTime,m.isCheck,a.applicationId,u.*,p.title from user u join application a on u.userId = a.userId join position  p on p.positionId = a.positionId join message m on a.applicationId = m.applicationId where a.hrId =#{hrId} and a.state = #{state} and p.positionId in ",
            "<foreach collection='positionIds' item='item' index='index' open='(' separator=',' close=')'>" ,
            "(#{item})",
            "</foreach>",
            "order by a.state DESC,m.msgSendTime DESC",
            "</script>" })
    List<InterviewDescBO> getInterviewByStateAndPosition(@Param("hrId")int hrId,@Param("state")int state,@Param("positionIds") List<Integer> positionIds);


    @Select("select m.news,a.state,m.msgSendTime,m.isCheck,a.applicationId,u.*,p.title \n"+
            "from user u join application a on u.userId = a.userId \n" +
            "join position  p on p.positionId = a.positionId \n" +
            "join message m on a.applicationId = m.applicationId \n" +
            "where a.hrId =#{hrId} and a.state = #{state}  \n"+
            "order by a.state DESC,m.msgSendTime DESC")
    List<InterviewDescBO> getInterviewByState(@Param("hrId")int hrId, @Param("state") int state);
}
