package qdu.java.recruit.mapper;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import qdu.java.recruit.entity.CompanyEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyMapperTest extends TestCase {
    @Autowired
    CompanyMapper companyMapper;
    @Test
    public void testGetCompanyById() {
        System.out.println(companyMapper.getCompanyById(1));
    }
    @Test
    public void testGetCompanyByCode() {
        System.out.println(companyMapper.getCompanyByCode("AL85685"));
    }
    @Test
    public void testSaveCompany() {
        CompanyEntity company=new CompanyEntity();
        company.setCompanyName("唯品会");
        company.setCompanyLogo(1);
        company.setDescription("很好的公司");
        company.setState(1);
        company.setCompanyCode("ABC123");
        company.setCompanyProperty("上市公司");
        company.setCompanyScale(2);
        company.setCompanyCity("广州");
        company.setCompanyIndustry("互联网");
        System.out.println(companyMapper.saveCompany(company));
    }

    @Test
    public void testUpdateCompany() {
        CompanyEntity company = companyMapper.getCompanyById(9);
        company.setDescription("非常好的公司");
        companyMapper.updateCompany(company);
    }
}