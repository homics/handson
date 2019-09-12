package com.homics.gateway.service;

import com.homics.gateway.controller.dto.UserActivityDto;
import com.homics.gateway.model.ActivityType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
public class UserActivityService {
    @Value("${app.user.url}")
    private String USER_URL;
    private RestTemplate restTemplate;

    public UserActivityService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void addLogin(String userName) {
        UserActivityDto userActivity = new UserActivityDto();
        userActivity.setUsername(userName);
        userActivity.setActivityDate(Instant.now());
        userActivity.setActivityType(ActivityType.LOGIN);

        restTemplate.postForEntity(String.format("%s/user/api/activity", USER_URL), userActivity, Void.class);
    }

    public void addLogout(String userName) {
        UserActivityDto userActivity = new UserActivityDto();
        userActivity.setUsername(userName);
        userActivity.setActivityDate(Instant.now());
        userActivity.setActivityType(ActivityType.LOGOUT);

        restTemplate.postForEntity(String.format("%s/user/api/activity", USER_URL), userActivity, Void.class);
    }

}
