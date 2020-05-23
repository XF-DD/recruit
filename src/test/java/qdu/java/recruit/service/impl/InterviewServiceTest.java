package qdu.java.recruit.service.impl;

import com.github.pagehelper.PageInfo;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import qdu.java.recruit.pojo.InterviewDescBO;
import qdu.java.recruit.service.InterviewService;

/**
 * @author ChenGuiHong
 * @create 2020-05-23  15:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InterviewServiceTest extends TestCase{

    @Autowired
    InterviewService interviewService;

    @Test
    public void getAllInterviceDescTest(){

        PageInfo<InterviewDescBO> pageInfo= interviewService.listInterviewInfos(1,1,12,2,null);

        System.out.println(pageInfo);

    }
}
