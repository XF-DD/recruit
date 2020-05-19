package qdu.java.recruit.service;

import qdu.java.recruit.entity.ResumeEntity;
import qdu.java.recruit.entity.UserEntity;
import qdu.java.recruit.pojo.PostedRecumeBO;

import java.util.List;

public interface ResumeService {

    ResumeEntity getResumeById(int userId);

    //5/16  陈淯
    List<PostedRecumeBO> getResumeByStateWithPosIds(int hrId, int state,List<Integer> positionIds);

    List<PostedRecumeBO> getResumeByState(int hrId, int state);

    //5/17 陈淯
    List<PostedRecumeBO> getAllResumeWithPosIds(int hrId,List<Integer> positionIds);

    List<PostedRecumeBO> getAllResume(int hrId);

    List<PostedRecumeBO> getInterviewResumeByHrId(int hrId);

    boolean updateResume(ResumeEntity resumeEntity);

    boolean createResume(ResumeEntity resumeEntity);

    //以下新增
    List<PostedRecumeBO> searchUser(int hrId, String keyword, int page, int limit);

    boolean sendNews(int state, int applicationId, String interviewsDesc, int hrId);
}
