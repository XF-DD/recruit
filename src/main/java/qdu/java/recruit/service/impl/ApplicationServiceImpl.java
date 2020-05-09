package qdu.java.recruit.service.impl;

import org.springframework.stereotype.Service;
import qdu.java.recruit.mapper.ApplicationMapper;
import qdu.java.recruit.pojo.ApplicationPositionHRBO;
import qdu.java.recruit.pojo.UserPositionHRBO;
import qdu.java.recruit.pojo.UserResumeHRBO;
import qdu.java.recruit.service.ApplicationService;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Resource
    private ApplicationMapper applicationMapper;

    @Override
    public boolean applyPosition(int resumeId, int positionId,int hrId,int userId) {

        //获取当前日期时间
        java.util.Date date = new java.util.Date();
        Timestamp recentTime = new Timestamp(date.getTime());

        int result = applicationMapper.saveApplication(recentTime, resumeId, positionId,hrId,userId);
        if (result > 0) {
            return true;
        }
        return false;
    }

    public List<UserPositionHRBO> selectAllResumeByHrId(int hrId){
        return applicationMapper.selectAllResumeByHrId(hrId);
    }

    //获取已看或者未看的简历   0---未看  1---已看
    public List<UserPositionHRBO> newOrOldResume(int hrId, int flag){
        return applicationMapper.newOrOldResume(hrId,flag);
    }


    //移除简历  状态置为-1
    public int removeResume(int applicationId) {
        return applicationMapper.updateResume(-1,applicationId);
    }

    @Override
    public int updateResumeState(int flag, int applicationId) {
        return applicationMapper.updateResume(flag,applicationId);
    }

    //安排面试
    public int arrangeInterview(String interviewsDesc,int applicationId,int flag){
        return applicationMapper.OperateInterviews(flag,interviewsDesc,applicationId);
    }


    //查看简历具体信息
    @Override
    public UserResumeHRBO checkResumeByAId(int applicationId) {
        return applicationMapper.checkResume(applicationId);
    }

    /**
     * 申请处理完成
     * @param resumeId
     * @return
     */
    @Override
    public List<ApplicationPositionHRBO> listApplyInfo(int resumeId){

        return applicationMapper.listAppPosHR(resumeId);
    }

    /**
     * 申请待处理
     * @param resumeId
     * @return
     */
    @Override
    public List<ApplicationPositionHRBO> listApplyInfoPub(int resumeId){

        return applicationMapper.listAppPosHRPub(resumeId);
    }


    @Override
    public List<ApplicationPositionHRBO> listApplyInfoByHr(int hrid) {
        return applicationMapper.listAppPosHR(hrid);

    }
}
