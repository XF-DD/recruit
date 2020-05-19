package qdu.java.recruit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import qdu.java.recruit.entity.ResumeEntity;
import qdu.java.recruit.mapper.ResumeMapper;
import qdu.java.recruit.pojo.PositionCategoryHRBO;
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

    /**
     * 5/16陈淯
     * 5/18陈淯  添加分页
     * 5/19陈淯  添加查找大于3面的简历
     */
    @Override
    public PageInfo<PostedRecumeBO> getResumeByStateWithPosIds(int hrId, int state, List<Integer> positionIds, int page, int limit) {

        PageHelper.startPage(page, limit);
        List<PostedRecumeBO> postedResumeBOList = null;
        if (state <= 4) {
            postedResumeBOList = resumeMapper.getResumeByStateWithPosIds(hrId, state, positionIds);
        } else {
            postedResumeBOList = resumeMapper.getResumeByStateGThreeWithPosIds(hrId, positionIds);
        }
        int total = postedResumeBOList.size();
        PageInfo<PostedRecumeBO> pagination = new PageInfo<>(postedResumeBOList);
        pagination.setTotal(total);

        return pagination;
    }

    /**
     * 5/18陈淯  添加分页
     * 5/19陈淯  添加查找大于3面的简历
     */
    @Override
    public PageInfo<PostedRecumeBO> getResumeByState(int hrId, int state, int page, int limit) {

        PageHelper.startPage(page, limit);
        List<PostedRecumeBO> postedResumeBOList = null;
        if (state <= 4) {
            postedResumeBOList = resumeMapper.getResumeByState(hrId, state);
        } else {
            postedResumeBOList = resumeMapper.getResumeByStateGThree(hrId);
        }

        int total = postedResumeBOList.size();
        PageInfo<PostedRecumeBO> pagination = new PageInfo<>(postedResumeBOList);
        pagination.setTotal(total);

        return pagination;
    }


    /**
     * 5/17 添加按positionIds查询
     * 5/18陈淯  添加分页
     */
    @Override
    public PageInfo<PostedRecumeBO> getAllResumeWithPosIds(int hrId, List<Integer> positionIds, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<PostedRecumeBO> postedResumeBOList = resumeMapper.getAllResumeWithPosIds(hrId, positionIds);

        int total = postedResumeBOList.size();

        PageInfo<PostedRecumeBO> pagination = new PageInfo<>(postedResumeBOList);
        pagination.setTotal(total);

        return pagination;
    }


    //5/18陈淯  添加分页
    @Override
    public PageInfo<PostedRecumeBO> getAllResume(int hrId, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<PostedRecumeBO> postedResumeBOList = resumeMapper.getAllResume(hrId);
        int total = postedResumeBOList.size();
        PageInfo<PostedRecumeBO> pagination = new PageInfo<>(postedResumeBOList);
        pagination.setTotal(total);
        return pagination;
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

    //5/19 黄少龙
    @Override
    public PageInfo<PostedRecumeBO> searchUser(int hrId, String keyword, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<PostedRecumeBO> searchList = resumeMapper.searchUser(hrId, "%" + keyword + "%");
        int total = searchList.size();
        PageInfo<PostedRecumeBO> searchListPageInfo = new PageInfo<>(searchList);
        searchListPageInfo.setTotal(total);
        return searchListPageInfo;
    }

    //5/19 陈淯  合并发送offer 和通知面试不通过
    @Override
    public boolean sendNews(int state, int applicationId, String interviewsDesc, int hrId) {
        int userId = resumeMapper.getUserId(applicationId);

        if (state == -3 || state == -2) {
            //设置application表状态
            if (resumeMapper.setState(state, applicationId) == 0) {
                return false;
            }
            //记录发送信息
            if (resumeMapper.sendOfferNews(state, applicationId, interviewsDesc, hrId) == 0) {
                return false;
            }
            if (state == -3) {
                //将已发送offer的其他申请状态设为-2(未通过)
                resumeMapper.setState1(-2, hrId, applicationId, userId);
            }
            return true;
        } else {
            //记录发送信息
            if (resumeMapper.sendOfferNews(state, applicationId, interviewsDesc, hrId) == 0) {
                return false;
            }
            return true;
        }
    }

    //5/19 黄少龙 搜索分页 按状态+关键字
    @Override
    public PageInfo<PostedRecumeBO> getResumeByTitleAndState(int hrId, String keyword, int state, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<PostedRecumeBO> postedResumeBOList = null;

        postedResumeBOList = resumeMapper.getResumeByTitleAndState(hrId, state, "%" + keyword + "%");

        int total = postedResumeBOList.size();
        PageInfo<PostedRecumeBO> pagination = new PageInfo<>(postedResumeBOList);
        pagination.setTotal(total);

        return pagination;
    }

    //5/19 黄少龙 搜索分页 按标题+状态+关键字
    @Override
    public PageInfo<PostedRecumeBO> getResumeByTitleAndStateWithPosIds(int hrId, int state, List<Integer> positionIds, String keyword, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<PostedRecumeBO> postedResumeBOList = null;
        postedResumeBOList = resumeMapper.getResumeByTitleAndStateWithPosIds(hrId, state, positionIds, "%" + keyword + "%");
        int total = postedResumeBOList.size();
        PageInfo<PostedRecumeBO> pagination = new PageInfo<>(postedResumeBOList);
        pagination.setTotal(total);
        return pagination;
    }


}
