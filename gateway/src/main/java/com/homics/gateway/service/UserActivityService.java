package com.homics.gateway.service;

import com.homics.gateway.controller.dto.UserActivityDto;
import com.homics.gateway.model.ActivityType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
public class UserActivityService {
    private final String USER_ACTIVITY_URL = "http://localhost:9001/user/api/activity";
    private RestTemplate restTemplate;

    public UserActivityService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void addLogin(String userName) {
        UserActivityDto userActivity = new UserActivityDto();
        userActivity.setUsername(userName);
        userActivity.setActivityDate(Instant.now());
        userActivity.setActivityType(ActivityType.LOGIN);

        restTemplate.postForEntity(USER_ACTIVITY_URL, userActivity, Void.class);
    }

    public void addLogout(String userName) {
        UserActivityDto userActivity = new UserActivityDto();
        userActivity.setUsername(userName);
        userActivity.setActivityDate(Instant.now());
        userActivity.setActivityType(ActivityType.LOGOUT);

        restTemplate.postForEntity(USER_ACTIVITY_URL, userActivity, Void.class);
    }

}
