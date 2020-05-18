package qdu.java.recruit.pojo;

import qdu.java.recruit.entity.UserEntity;

/**
 * 投递到hr端的简历
 * @author PocketKnife
 * @create 2020-05-09  18:18
 */
public class PostedRecumeBO extends UserEntity{

    private int applicationId;

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getApplicationId() {

        return applicationId;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
