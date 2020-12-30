package safety.model;

import java.util.Date;

public class Reviews {
    protected int reviewId;
    protected String content;
    protected Date createdTime;
    protected Users user;
    protected SafetyEvent event;

    public Reviews(int reviewId, String content, Date createdTime, Users user, SafetyEvent event) {
        this.reviewId = reviewId;
        this.content = content;
        this.createdTime = createdTime;
        this.user = user;
        this.event = event;
    }

    public Reviews(int reviewId) {
        this.reviewId = reviewId;
    }

    public Reviews(String content, Date createdTime, Users user, SafetyEvent event) {
        this.content = content;
        this.createdTime = createdTime;
        this.user = user;
        this.event = event;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public SafetyEvent getEvent() {
        return event;
    }

    public void setEvent(SafetyEvent event) {
        this.event = event;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

}
