package qdu.java.recruit.service;


import qdu.java.recruit.pojo.ApplicationPositionHRBO;
import qdu.java.recruit.pojo.ApplicationResumeHRBO;

import java.util.List;

public interface ApplicationService {

    boolean applyPosition(int resumeId, int positionId,int hrId,int userId);

    int updateResumeState(int state,int applicationId);

    int arrangeInterview(int applicationId,int flag);

    List<ApplicationPositionHRBO> listApplyInfo(int resumeId);

    List<ApplicationPositionHRBO> listApplyInfoPub(int resumeId);

    List<ApplicationPositionHRBO> listApplyInfoByHr(int hrid);

    ApplicationResumeHRBO getResumeHRBO(int applicationId);

}
