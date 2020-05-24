package qdu.java.recruit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import qdu.java.recruit.mapper.ApplicationMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
//	ApplicationMapper applicationMapper;
	@Test
	public void contextLoads() {
//		System.out.println(applicationMapper.test().getInterviewsDesc());
	}

}
