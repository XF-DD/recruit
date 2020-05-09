package qdu.java.recruit.pojo;

import qdu.java.recruit.entity.UserEntity;

import java.util.Date;

public class UserPositionHRBO extends UserEntity {

    private int applicationId;

    private String title;

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
