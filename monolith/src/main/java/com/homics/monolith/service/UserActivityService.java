package com.homics.monolith.service;

import com.homics.monolith.controller.dto.UserActivityDto;
import com.homics.monolith.model.ActivityType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
public class UserActivityService {

    private RestTemplate restTemplate;

    public UserActivityService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void addLogin(String userName) {
        UserActivityDto userActivity = new UserActivityDto();
        userActivity.setUsername(userName);
        userActivity.setActivityDate(Instant.now());
        userActivity.setActivityType(ActivityType.LOGIN);
        post(userActivity);
    }

    public void addLogout(String userName) {
        UserActivityDto userActivity = new UserActivityDto();
        userActivity.setUsername(userName);
        userActivity.setActivityDate(Instant.now());
        userActivity.setActivityType(ActivityType.LOGOUT);
        post(userActivity);
    }

    public void post(UserActivityDto userActivityDto) {
        restTemplate.postForEntity("http://localhost:9001/user/api/activity", userActivityDto, Void.class);
    }

}
