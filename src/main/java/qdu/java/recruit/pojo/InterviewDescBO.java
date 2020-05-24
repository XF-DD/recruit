package qdu.java.recruit.pojo;

import qdu.java.recruit.entity.UserEntity;

import java.sql.Timestamp;

/**
 * 面试信息
 * @author ChenGuiHong
 * @create 2020-05-18  22:31
 */
public class InterviewDescBO extends UserEntity{

    private int applicationId;
    private String news;
    private int state;
    private Timestamp msgSendTime;
    private String title;
    private int isCheck;


    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Timestamp getMsgSendTime() {
        return msgSendTime;
    }

    public void setMsgSendTime(Timestamp msgSendTime) {
        this.msgSendTime = msgSendTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }

}
