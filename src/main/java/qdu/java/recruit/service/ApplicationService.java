package qdu.java.recruit.service;


import qdu.java.recruit.pojo.ApplicationPositionHRBO;
import qdu.java.recruit.pojo.ApplicationResumeHRBO;

import java.util.List;

public interface ApplicationService {

    boolean applyPosition(int resumeId, int positionId);

    int updateResumeState(int state,int applicationid);

    List<ApplicationPositionHRBO> listApplyInfo(int resumeId);

    List<ApplicationPositionHRBO> listApplyInfoPub(int resumeId);

    List<ApplicationPositionHRBO> listApplyInfoByHr(int hrid);

    ApplicationResumeHRBO getResumeHRBO(int applicationId);

}
