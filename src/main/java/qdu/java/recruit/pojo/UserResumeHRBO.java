package qdu.java.recruit.pojo;

import qdu.java.recruit.entity.UserEntity;

public class UserResumeHRBO extends UserEntity {

    private int resumeId;
    private String ability;
    private String internship;
    private String workExperience;
    private String certificate;
    private String jobDesire;

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

}
