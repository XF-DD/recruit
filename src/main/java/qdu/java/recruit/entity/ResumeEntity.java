package qdu.java.recruit.entity;

/**
 * @Author: XF-DD
 * @Date: 20/05/21 16:58

 * 新增 ：
 * 1. interest 兴趣爱好
 * 2. education 教育经历
 * 3. annex 附件路径
 *
 * 废弃:
 * 1. internship 实习
 * 2. jobDesire 工作期望
 */
public class ResumeEntity {
    private int resumeId;
    private String ability;
    private String internship;
    private String workExperience;
    private String certificate;
    private String jobDesire;
    private int userId;

    private String annex;
    private String education;
    private String interest;

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getInternship() {
        return internship;
    }

    public void setInternship(String internship) {
        this.internship = internship;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getJobDesire() {
        return jobDesire;
    }

    public void setJobDesire(String jobDesire) {
        this.jobDesire = jobDesire;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}
