package qdu.java.recruit.service;

import qdu.java.recruit.entity.ResumeEntity;
import qdu.java.recruit.pojo.PostedRecumeBO;

import java.util.List;

public interface ResumeService {

    ResumeEntity getResumeById(int userId);

    List<PostedRecumeBO> getResumeByHrId(int hrId);

    List<PostedRecumeBO> getSeenResumeByHrId(int hrId);

    List<PostedRecumeBO> getInterviewResumeByHrId(int hrId);

    List<PostedRecumeBO> getFailedResumeByHrId(int hrId);


    List<PostedRecumeBO> getAbandonResumeByHrId(int hrId);

    boolean updateResume(ResumeEntity resumeEntity);

    boolean createResume(ResumeEntity resumeEntity);
}
