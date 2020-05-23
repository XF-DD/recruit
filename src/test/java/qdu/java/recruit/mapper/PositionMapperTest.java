package qdu.java.recruit.mapper;

import junit.framework.TestCase;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import qdu.java.recruit.entity.PositionEntity;
import qdu.java.recruit.pojo.PositionCompanyBO;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PositionMapperTest extends TestCase {

    @Autowired
    PositionMapper positionMapper;

    @Test
    public void testUpdatePosition() {
        PositionEntity position = positionMapper.getPosition(1);
        position.setBenefits("福利很好");
        positionMapper.updatePosition(position);
    }

    public void testSavePosition() {
    }

//    @Param("keyword") String keyword,
//    @Param("order") String order,
//    @Param("workCity") String workCity,
//    @Param("salaryDown") String salaryDown,
//    @Param("salaryUp") String salaryUp,
//    @Param("companyProperty") String companyProperty,
//    @Param("companyScale") int companyScale,
//    @Param("companyIndustry") String companyIndustry)
    @Test
    public void testListSearchPos() {
        ArrayList<PositionCompanyBO> list = positionMapper.listSearchPos(
                "阿里",
                "hits",
                null,
                "5000",
                "20000",
                null,
                1,
                "互联网"
        );
        System.out.println(list);
    }
}