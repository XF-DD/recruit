package qdu.java.recruit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import qdu.java.recruit.mapper.ApplicationMapper;
import qdu.java.recruit.pojo.UserPositionHRBO;
import qdu.java.recruit.service.ApplicationService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationServiceTest {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationMapper applicationMapper;

//    @Test
//    public void applyPositionTest(){
//        boolean result = applicationService.applyPosition(5,1,1);
//        Assert.assertEquals(true,result);
//    }

    @Test
    public void applyPositionTest() {
        List<UserPositionHRBO> resumeAndUserInfoBOS = applicationMapper.newOrOldResume(1, 1);
        for (int i = 0; i <resumeAndUserInfoBOS.size();  i++) {
            System.out.println(resumeAndUserInfoBOS.get(i).getUserId());
        }
    }

}
