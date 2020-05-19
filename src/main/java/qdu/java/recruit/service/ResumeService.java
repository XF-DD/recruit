package qdu.java.recruit.service;

import com.github.pagehelper.PageInfo;
import qdu.java.recruit.entity.ResumeEntity;
import qdu.java.recruit.entity.UserEntity;
import qdu.java.recruit.pojo.PostedRecumeBO;

import java.util.List;

public interface ResumeService {

    ResumeEntity getResumeById(int userId);

    //5/16  陈淯   添加分页
    PageInfo<PostedRecumeBO>  getResumeByStateWithPosIds(int hrId, int state, List<Integer> positionIds,int page,int limit);
    //5/18  陈淯  添加分页
    PageInfo<PostedRecumeBO> getResumeByState(int hrId, int state,int page,int limit);

    //5/17 陈淯 添加按positionIds查询 5/18陈淯 添加分页
    PageInfo<PostedRecumeBO> getAllResumeWithPosIds(int hrId,List<Integer> positionIds,int page,int limit);

    //5/18陈淯 添加分页
    PageInfo<PostedRecumeBO> getAllResume(int hrId,int page,int limit);

    List<PostedRecumeBO> getInterviewResumeByHrId(int hrId);

    boolean updateResume(ResumeEntity resumeEntity);

    boolean createResume(ResumeEntity resumeEntity);

    //以下新增
    List<PostedRecumeBO> searchUser(int hrId, String keyword, int page, int limit);

    boolean sendNews(int state, int applicationId, String interviewsDesc, int hrId);
}
