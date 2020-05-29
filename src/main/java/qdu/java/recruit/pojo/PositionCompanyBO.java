package qdu.java.recruit.pojo;

import qdu.java.recruit.entity.PositionEntity;

public class PositionCompanyBO extends PositionEntity {

    private int companyId;
    private String companyName;
    private int companyLogo;
    private String description;
    private int state;
    private String companyCode;
    private String companyCity;
    private String companyProperty;
    private int companyScale;//0：少于15人 1：15-50人 2：50-150人  3：150人-500人 4：500-2000人 5：2000人以上
    private String companyIndustry;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(int companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyProperty() {
        return companyProperty;
    }

    public void setCompanyProperty(String companyProperty) {
        this.companyProperty = companyProperty;
    }

    public int getCompanyScale() {
        return companyScale;
    }

    public void setCompanyScale(int companyScale) {
        this.companyScale = companyScale;
    }

    public String getCompanyIndustry() {
        return companyIndustry;
    }

    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry;
    }

    @Override
    public String toString() {
        return "PositionCompanyBO{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", companyLogo=" + companyLogo +
                ", description='" + description + '\'' +
                ", state=" + state +
                ", companyCode='" + companyCode + '\'' +
                ", companyCity='" + companyCity + '\'' +
                ", companyProperty='" + companyProperty + '\'' +
                ", companyScale=" + companyScale +
                ", companyIndustry='" + companyIndustry + '\'' +
                '}';
    }
}
