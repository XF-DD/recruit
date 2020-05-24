package qdu.java.recruit.service;


import com.github.pagehelper.PageInfo;

import qdu.java.recruit.entity.HREntity;

public interface HRService {

    HREntity getHR(int hrId);

    boolean updateHR(HREntity HREntity);

    boolean registerHR(HREntity HREntity);

    boolean loginHR(String mobile, String password);

    HREntity getHRByMobile(String mobile);


    PageInfo<HREntity> searchHr(int hrId, int companyId,int page, int limit);

    boolean deleteHR(int hrid, int companyId);

}
