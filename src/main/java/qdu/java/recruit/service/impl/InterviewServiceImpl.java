package qdu.java.recruit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import qdu.java.recruit.mapper.InterviewMapper;
import qdu.java.recruit.pojo.InterviewDescBO;
import qdu.java.recruit.service.InterviewService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChenGuiHong
 * @create 2020-05-18  22:46
 */
@Service
public class InterviewServiceImpl implements InterviewService{

    @Resource
    private InterviewMapper interviewMapper;
    @Override
    public PageInfo<InterviewDescBO> listInterviewInfos(int hrId, int page, int limit,int state, List<Integer> positionIds) {
        PageHelper.startPage(page,limit);
        List<InterviewDescBO> interList = null;
        if(positionIds == null){
            interList = interviewMapper.getAllOrMoreInterviewDesc(hrId,state);
        }else{
            interList = interviewMapper.getAllOrMoreInterviewDescByPosition(hrId,state,positionIds);
        }
        PageInfo<InterviewDescBO> pagination = new PageInfo<>(interList);
        return pagination;
    }

    @Override
    public PageInfo<InterviewDescBO> listInterviewInfoByState(int hrId, int page, int limit, int state,List<Integer> positionIds) {
        PageHelper.startPage(page, limit);
        List<InterviewDescBO> interList = null;
        if(positionIds == null) {
            interList = listInterviewInfo(hrId, state);
        }else {
            interList = listInterviewInfo(hrId, state, positionIds);
        }
        PageInfo<InterviewDescBO> pagination = new PageInfo<>(interList);
        return pagination;
    }

    //以面试轮次和职位查询面试管理信息
    @Override
    public List<InterviewDescBO> listInterviewInfo(int hrId, int state, List<Integer> positionIds) {
        return interviewMapper.getInterviewByStateAndPosition(hrId,state,positionIds);
    }
    //以面试轮次查询面试管理信息
    @Override
    public List<InterviewDescBO> listInterviewInfo(int hrId, int state) {
        return interviewMapper.getInterviewByState(hrId,state);
    }


}
