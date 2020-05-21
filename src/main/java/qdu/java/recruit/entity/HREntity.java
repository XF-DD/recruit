package qdu.java.recruit.entity;

/**
 *@Author: XF-DD
 *@Date: 20/05/21 16:58
 *
 * 新增：
 * 1. companyId  公司Id
 * 2. power  权限字段，值1 为root权限的hr  ，值为0为普通hr
 *
 * 废弃
 * 1. departmentId 部门id
 */
public class HREntity {

    private int hrId;
    private String hrMobile;
    private String hrPassword;
    private String hrName;
    private String hrEmail;
    private String description;
    private int departmentId;

    private int companyId;
    private int power;


    public int getHrId() {
        return hrId;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
    }

    public String getHrMobile() {
        return hrMobile;
    }

    public void setHrMobile(String hrMobile) {
        this.hrMobile = hrMobile;
    }

    public String getHrPassword() {
        return hrPassword;
    }

    public void setHrPassword(String hrPassword) {
        this.hrPassword = hrPassword;
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public String getHrEmail() {
        return hrEmail;
    }

    public void setHrEmail(String hrEmail) {
        this.hrEmail = hrEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
