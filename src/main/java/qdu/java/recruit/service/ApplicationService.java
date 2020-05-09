package qdu.java.recruit.service;


import qdu.java.recruit.pojo.ApplicationPositionHRBO;
import qdu.java.recruit.pojo.UserPositionHRBO;
import qdu.java.recruit.pojo.UserResumeHRBO;

import java.util.List;

public interface ApplicationService {

    boolean applyPosition(int resumeId, int positionId,int hrId,int userId);

    List<UserPositionHRBO> selectAllResumeByHrId(int hrId);

    List<UserPositionHRBO> newOrOldResume(int hrId, int flag);

    int removeResume(int applicationId);

    int updateResumeState(int flag,int applicationId);

    int arrangeInterview(String interviewsDesc,int applicationId,int flag);

    UserResumeHRBO checkResumeByAId(int applicationId);


    List<ApplicationPositionHRBO> listApplyInfo(int resumeId);

    List<ApplicationPositionHRBO> listApplyInfoPub(int resumeId);

    List<ApplicationPositionHRBO> listApplyInfoByHr(int hrid);




}
