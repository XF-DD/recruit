package qdu.java.recruit.mapper;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import qdu.java.recruit.entity.ResumeEntity;
import qdu.java.recruit.entity.UserEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResumeMapperTest extends TestCase {

    @Autowired
    ResumeMapper resumeMapper;

    @Autowired
    UserMapper userMapper;

    @Test
    public void testSaveResume() {
        int i = resumeMapper.saveResume(new ResumeEntity(
                1,
                "能力强劲",
                "无实习",
                "没工作",
                "没有奖",
                "想拿高薪",
                1,
                null,
                "广东财经大学",
                "唱跳rap篮球"
        ));
        System.out.println(i);
    }
    @Test
    public void testCreateResume() {
        resumeMapper.createResume(new ResumeEntity(
                1,
                "能力强劲",
                "无实习",
                "没工作",
                "没有奖",
                "想拿高薪",
                1,
                null,
                "广东财经大学",
                "唱跳rap篮球"
        ));
    }
}