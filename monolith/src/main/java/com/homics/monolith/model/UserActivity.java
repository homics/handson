package com.homics.monolith.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
public class UserActivity {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private Instant activityDate;

    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Instant getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Instant activityDate) {
        this.activityDate = activityDate;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserActivity that = (UserActivity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(activityDate, that.activityDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, activityDate);
    }

    @Override
    public String toString() {
        return "UserActivity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", activityDate=" + activityDate +
                '}';
    }
}
