package qdu.java.recruit.service.impl;

import com.github.pagehelper.PageInfo;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import qdu.java.recruit.pojo.PositionCompanyBO;

import javax.xml.ws.soap.Addressing;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PositionServiceImplTest extends TestCase {

    /**
     * 分页职位搜索
     *
     * @param keyword
     * @param orderBy
     * @param workCity
     * @param salaryDown
     * @param salaryUp
     * @param page
     * @param limit
     * @return
     */
    @Autowired
    PositionServiceImpl positionService;

    @Test
    public void testSearchPosition() {
        PageInfo<PositionCompanyBO> positionCompanyBOPageInfo = positionService.searchPosition(
                "java",
                "hits",
                null,
                "5000",
                "20000",
                1,
                6
        );
        System.out.println(positionCompanyBOPageInfo);
    }
}