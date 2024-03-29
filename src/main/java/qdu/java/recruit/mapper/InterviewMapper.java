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

    //查询所有轮次或者更多的所有面试信息（不同面试轮数，所有职位）。
    @Select("select m.news,a.state,m.msgSendTime,m.isCheck,a.applicationId,u.*,p.title \n"+
            "from user u join application a on u.userId = a.userId \n"+
            "join position  p on p.positionId = a.positionId \n"+
            "join message m on m.state = a.state and m.hrId = p.hrIdPub and m.userId = a.userId and m.applicationId = a.applicationId \n"+
            "where a.hrId = #{hrId} and a.state >= #{state} \n"+
            "and m.msgSendTime in (select substring_index(GROUP_CONCAT(msgSendTime order by msgSendTime desc),',',1)  \n"+
            "from user u join application a on u.userId = a.userId  \n"+
            "join position  p on p.positionId = a.positionId  \n"+
            "join message m on m.state = a.state and m.hrId = p.hrIdPub and m.userId = a.userId and m.applicationId = a.applicationId  \n"+
            "where a.hrId =#{hrId} and m.state >= #{state}  \n"+
            "group by m.applicationId,a.state)  \n"+
            "order by a.state DESC,m.msgSendTime DESC ")
    List<InterviewDescBO> getAllOrMoreInterviewDesc(@Param("hrId") int hrId, @Param("state") int state);

    //查询所有轮次或者更多的对应职位的所有面试信息（不同面试轮数，对应职位）。
    @Select({
            "<script>",
            "select m.news,a.state,m.msgSendTime,m.isCheck,a.applicationId,u.*,p.title " +
            "from user u join application a on u.userId = a.userId " +
            "join position  p on p.positionId = a.positionId " +
            "join message m on m.state = a.state and m.hrId = p.hrIdPub and m.userId = a.userId and m.applicationId = a.applicationId " +
            "where a.hrId =#{hrId} and a.state >= #{state} and p.positionId in ",
            "<foreach collection='positionIds' item='item' index='index' open='(' separator=',' close=')'>",
            "(#{item})",
            "</foreach>",
            "and m.msgSendTime in (select substring_index(GROUP_CONCAT(msgSendTime order by msgSendTime desc),',',1)  "+
            "from user u join application a on u.userId = a.userId  "+
            "join position  p on p.positionId = a.positionId  "+
            "join message m on m.state = a.state and m.hrId = p.hrIdPub and m.userId = a.userId and m.applicationId = a.applicationId  "+
            "where a.hrId =#{hrId} and m.state >= #{state}  "+
            "group by m.applicationId ,a.state)  "+
            "order by a.state DESC,m.msgSendTime DESC",
            "</script>"})
    List<InterviewDescBO> getAllOrMoreInterviewDescByPosition(@Param("hrId") int hrId, @Param("state") int state, @Param("positionIds") List<Integer> positionIds);

    @Select({"<script>",
            "select m.news,a.state,m.msgSendTime,m.isCheck,a.applicationId,u.*,p.title " +
            "from user u join application a on u.userId = a.userId " +
            "join position  p on p.positionId = a.positionId " +
            "join message m on m.state = a.state and m.hrId = p.hrIdPub and m.userId = a.userId and m.applicationId = a.applicationId " +
            "where a.hrId =#{hrId} and m.state = #{state} and p.positionId in ",
            "<foreach collection='positionIds' item='item' index='index' open='(' separator=',' close=')'>" ,
            "(#{item})",
            "</foreach>",
            "and m.msgSendTime in (select substring_index(GROUP_CONCAT(msgSendTime order by msgSendTime desc),',',1)  "+
            "from user u join application a on u.userId = a.userId "+
            "join position  p on p.positionId = a.positionId  "+
            "join message m on m.state = a.state and m.hrId = p.hrIdPub and m.userId = a.userId and m.applicationId = a.applicationId  "+
            "where a.hrId =#{hrId} and m.state = #{state} "+
            "group by m.applicationId ) "+
            "order by a.state DESC,m.msgSendTime DESC",
            "</script>" })
    List<InterviewDescBO> getInterviewByStateAndPosition(@Param("hrId") int hrId, @Param("state") int state, @Param("positionIds") List<Integer> positionIds);


    //查询所有轮次或者更多的对应面试轮次面试信息（对应面试轮数，所有职位）。
    @Select("select m.news,a.state,m.msgSendTime,m.isCheck,a.applicationId,u.*,p.title \n"+
        "from user u join application a on u.userId = a.userId \n" +
        "join position  p on p.positionId = a.positionId \n" +
        "join message m on m.state = a.state and m.hrId = p.hrIdPub and m.userId = a.userId and m.applicationId = a.applicationId \n" +
        "where a.hrId =#{hrId} and m.state = #{state}  \n"+
        "and m.msgSendTime in (select substring_index(GROUP_CONCAT(msgSendTime order by msgSendTime desc),',',1)  \n"+
        "from user u join application a on u.userId = a.userId  \n"+
        "join position  p on p.positionId = a.positionId  \n"+
        "join message m on m.state = a.state and m.hrId = p.hrIdPub and m.userId = a.userId and m.applicationId = a.applicationId  \n"+
        "where a.hrId =#{hrId} and m.state = #{state}  \n"+
        "group by m.applicationId )  \n"+
        "order by a.state DESC,m.msgSendTime DESC")
    List<InterviewDescBO> getInterviewByState(@Param("hrId") int hrId, @Param("state") int state);
}
