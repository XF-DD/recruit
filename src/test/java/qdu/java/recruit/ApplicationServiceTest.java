package qdu.java.recruit;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import qdu.java.recruit.service.ApplicationService;
import qdu.java.recruit.service.ResumeService;
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
