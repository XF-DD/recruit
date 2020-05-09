package qdu.java.recruit.service;

import qdu.java.recruit.entity.ResumeEntity;
import qdu.java.recruit.pojo.PostedRecumeBO;

import java.util.List;

public interface ResumeService {

    ResumeEntity getResumeById(int userId);

    List<PostedRecumeBO> getResumeByState(int hrId,int state);

    List<PostedRecumeBO> getAllResume(int hrId);

    List<PostedRecumeBO> getInterviewResumeByHrId(int hrId);

    boolean updateResume(ResumeEntity resumeEntity);

    boolean createResume(ResumeEntity resumeEntity);
}
