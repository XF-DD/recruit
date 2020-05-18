package qdu.java.recruit.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import qdu.java.recruit.entity.ResumeEntity;
import qdu.java.recruit.entity.UserEntity;
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

    //以下新增
    @Override
    public List<PostedRecumeBO> searchUser(int hrId, String keyword, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<PostedRecumeBO> searchList = resumeMapper.searchUser(hrId, "%" + keyword + "%");
        return searchList;
    }

    @Override
    public boolean sendNews(int state, int applicationId, String interviewsDesc, int hrId) {
        int userId = resumeMapper.getUserId(applicationId);
        //将已发送offer的申请状态设为-3
        if (resumeMapper.setState(-3,applicationId)==0){
            return false;
        }

        //记录发送信息
        if(resumeMapper.sendOfferNews(state, applicationId, interviewsDesc, hrId)==0){
            return false;
        }

        //将已发送offer的其他申请状态设为-2(未通过)
        if (resumeMapper.setState1(-2, hrId, applicationId, userId)==0){
            return false;
        }

        //sssasasasas
        return true;
    }

}
