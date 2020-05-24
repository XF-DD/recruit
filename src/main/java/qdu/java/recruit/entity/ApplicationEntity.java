package qdu.java.recruit.entity;

import java.sql.Timestamp;

public class ApplicationEntity {
    private int applicationId;
    private int applicationState;
    private Timestamp recentTime;

    //简历
    private int resumeId;
    //岗位
    private int positionId;
    //hr
    private int hrId;

    private String interviewsDesc;
    private int userId;

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getApplicationState() {
        return applicationState;
    }

    public void setApplicationState(int applicationState) {
        this.applicationState = applicationState;
    }

    public Timestamp getRecentTime() {
        return recentTime;
    }

    public void setRecentTime(Timestamp recentTime) {
        this.recentTime = recentTime;
    }


    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public int getHrId() {
        return hrId;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
    }

    public void setInterviewsDesc(String interviewsDesc) {
        this.interviewsDesc = interviewsDesc;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getInterviewsDesc() {
        return interviewsDesc;
    }

    public int getUserId() {
        return userId;
    }
}
