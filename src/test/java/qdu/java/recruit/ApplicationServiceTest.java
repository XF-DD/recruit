package qdu.java.recruit;

import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import qdu.java.recruit.mapper.ApplicationMapper;
import qdu.java.recruit.pojo.UserPositionHRBO;
import qdu.java.recruit.mapper.ResumeMapper;
import qdu.java.recruit.pojo.PostedRecumeBO;
import qdu.java.recruit.service.ApplicationService;
import qdu.java.recruit.service.ResumeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationServiceTest {

    @Autowired
    private ApplicationService applicationService;
    private ResumeService resumeService;

//    @Test
//    public void applyPositionTest(){
//        List<Integer> list = Arrays.asList(15,16);
//        PageInfo<PostedRecumeBO> test = resumeService.getAllResumeWithPosIds(6,list,1,6);
//
//    }

}
