package qdu.java.recruit.pojo;

import qdu.java.recruit.entity.UserEntity;
import qdu.java.recruit.util.StateUtil;

/**
 * 投递到hr端的简历
 *
 * @author PocketKnife
 * @create 2020-05-09  18:18
 */
public class PostedRecumeBO extends UserEntity {

    private int applicationId;

    private String title;

    private int state;

    private String stateName;

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        setStateName(StateUtil.getState(state));
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
