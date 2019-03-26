package com.homics.useractivity.controller.dto;

import com.homics.useractivity.model.ActivityType;

import java.time.Instant;

public class UserActivityDto {

    private String username;
    private ActivityType activityType;
    private Instant activityDate;

    public UserActivityDto() {
    }

    public UserActivityDto(String username, ActivityType activityType, Instant activityDate) {
        this.username = username;
        this.activityType = activityType;
        this.activityDate = activityDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public Instant getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Instant activityDate) {
        this.activityDate = activityDate;
    }

    @Override
    public String toString() {
        return "UserActivityDto{" +
                "username='" + username + '\'' +
                ", activityType=" + activityType +
                ", activityDate=" + activityDate +
                '}';
    }
}
