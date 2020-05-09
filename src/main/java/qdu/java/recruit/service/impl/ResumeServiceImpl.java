package qdu.java.recruit.service.impl;

import org.springframework.stereotype.Service;
import qdu.java.recruit.entity.ResumeEntity;
import qdu.java.recruit.mapper.ResumeMapper;
import qdu.java.recruit.pojo.PostedRecumeBO;
import qdu.java.recruit.service.ResumeService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Resource
    private ResumeMapper resumeMapper;

    @Override
    public ResumeEntity getResumeById(int userId) {

        return resumeMapper.getResumeById(userId);
    }

    @Override
    public List<PostedRecumeBO> getResumeByState(int hrId, int state) {
        return resumeMapper.getResumeByState(hrId,state);
    }

    @Override
    public List<PostedRecumeBO> getAllResume(int hrId) {
        return resumeMapper.getAllResume(hrId);
    }


    @Override
    public List<PostedRecumeBO> getInterviewResumeByHrId(int hrId) {
        return resumeMapper.getInterviewResume(hrId);
    }


    @Override
    public boolean updateResume(ResumeEntity resumeEntity) {

        if (resumeMapper.saveResume(resumeEntity) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean createResume(ResumeEntity resumeEntity) {

        if (resumeMapper.createResume(resumeEntity) > 0) {
            return true;
        }
        return false;
    }

}
