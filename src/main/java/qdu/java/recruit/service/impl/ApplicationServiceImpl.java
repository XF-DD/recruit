package qdu.java.recruit.service.impl;

import org.springframework.stereotype.Service;
import qdu.java.recruit.mapper.ApplicationMapper;
import qdu.java.recruit.pojo.ApplicationPositionHRBO;
import qdu.java.recruit.pojo.ApplicationResumeHRBO;
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
       /* if (applicationMapper.getApplication(resumeId, positionId) == null) {
            int result = applicationMapper.saveApplication(recentTime, resumeId, positionId);
            return result > 0;*/
        }
        return false;
    }
    //安排面试
    @Override
    public int arrangeInterview(int applicationId,int flag){
        return applicationMapper.OperateInterviews(flag,applicationId);
    }

    @Override
    public int updateResumeState(int state, int applicationId) {
        return applicationMapper.updateResume(state,applicationId);
    }

    /**
     * 申请处理完成
     * @param resumeId
     * @return
     */
    @Override

    public List<ApplicationPositionHRBO> listApplyInfo(int resumeId) {

        return applicationMapper.listAppPosHR(resumeId);
    }

    /**
     * 申请待处理
<<<<<<< HEAD
=======
     *
>>>>>>> a1d05ad28a46471b20ed392c6f022a48212f56e4
     * @param resumeId
     * @return
     */
    @Override

    public List<ApplicationPositionHRBO> listApplyInfoPub(int resumeId) {


        return applicationMapper.listAppPosHRPub(resumeId);
    }


    @Override
    public List<ApplicationPositionHRBO> listApplyInfoByHr(int hrid) {
        return applicationMapper.listAppPosHR(hrid);

    }

    @Override
    public ApplicationResumeHRBO getResumeHRBO(int applicationId) {
        return applicationMapper.getApplicationResumeHRBO(applicationId);
    }
}
