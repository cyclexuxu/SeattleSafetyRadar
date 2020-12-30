package safety.model;

import java.util.Date;

public class UserSearchHistory {
    protected int searchId;
    protected Date createdTime;
    protected Users user;
    protected EventType eventType;
    protected Neighborhoods neighborhood;
    protected Double rating;

    public enum EventType {
        CRIME, FIRE, POLICE_SHOOTING
    }

    public UserSearchHistory(int searchId, Date createdTime, EventType eventType, Users user,
            Neighborhoods neighborhood, Double rating) {
        this.searchId = searchId;
        this.createdTime = createdTime;
        this.eventType = eventType;
        this.user = user;
        this.neighborhood = neighborhood;
        this.rating = rating;
    }

    public UserSearchHistory(Date createdTime, EventType eventType, Users user, Neighborhoods neighborhood,
            Double rating) {
        this.createdTime = createdTime;
        this.user = user;
        this.eventType = eventType;
        this.neighborhood = neighborhood;
        this.rating = rating;
    }

    // public UserSearchHistory(int searchId2, java.util.Date createdTime2,
    // EventType eventType2, Users user2,
    // Neighborhoods neighborhoods, Double rating2) {
    // }

    public int getSearchId() {
        return this.searchId;
    }

    public void setSearchId(int searchId) {
        this.searchId = searchId;
    }

    public Date getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Users getUser() {
        return this.user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Neighborhoods getNeighborhood() {
        return this.neighborhood;
    }

    public void setNeighborhood(Neighborhoods neighborhood) {
        this.neighborhood = neighborhood;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

}
