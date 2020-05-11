package qdu.java.recruit.service;

import qdu.java.recruit.entity.ResumeEntity;
import qdu.java.recruit.entity.UserEntity;
import qdu.java.recruit.pojo.PostedRecumeBO;

import java.util.List;

public interface ResumeService {

    ResumeEntity getResumeById(int userId);

    List<PostedRecumeBO> getResumeByState(int hrId,int state);

    List<PostedRecumeBO> getAllResume(int hrId);

    List<PostedRecumeBO> getInterviewResumeByHrId(int hrId);

    boolean updateResume(ResumeEntity resumeEntity);

    boolean createResume(ResumeEntity resumeEntity);

    //以下新增
    List<UserEntity> searchUser(int hrId, String keyword, int page, int limit);

    boolean sendNews(int state, int applicationId, String interviewsDesc, int hrId);
}
