package qdu.java.recruit.service;

import com.github.pagehelper.PageInfo;
import qdu.java.recruit.pojo.InterviewDescBO;

import java.util.List;

/**
 * @author ChenGuiHong
 * @create 2020-05-18  22:44
 */
public interface InterviewService {


    PageInfo<InterviewDescBO> listInterviewInfos(int hrId,int page,int limit,int state, List<Integer> positionIds);

    PageInfo<InterviewDescBO> listInterviewInfoByState(int hrId, int page , int limit , int state, List<Integer> positionIds);

    List<InterviewDescBO> listInterviewInfo(int hrId,int state,List<Integer> positionIds);

    List<InterviewDescBO> listInterviewInfo(int hrId, int state);
}
