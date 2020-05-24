package qdu.java.recruit.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import qdu.java.recruit.entity.CompanyEntity;

/**
 * int companyId;
 * String companyName;
 * int companyLogo;
 * String description;
 * int state;
 * String companyCode;
 * String companyCity;
 * String companyProperty;
 * String companyScale;//0：
 * String companyIndustry;
 * String phone;
 */
public interface CompanyMapper {

    @Select("select * from company where companyId = #{comId}")
    CompanyEntity getCompanyById(@Param("comId") int comId);

    @Select("select * from company where companyCode = #{companyCode}")
    CompanyEntity getCompanyByCode(@Param("companyCode") String companyCode);

    //增加 companyCity companyProperty companyScale companyIndustry
    @Insert({"insert into company(companyName,companyLogo,description,state,companyCode,companyCity,companyProperty,companyScale,companyIndustry)"
            +"values(#{companyName},#{companyLogo},#{description},#{state},#{companyCode},#{companyCity},#{companyProperty},#{companyScale},#{companyIndustry})"})
    int saveCompany(CompanyEntity companyEntity);

    //+修改公司信息
    int updateCompany(CompanyEntity companyEntity);
}
