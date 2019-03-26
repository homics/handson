package com.homics.monolith.service;

import com.homics.monolith.controller.dto.UserActivityDto;
import com.homics.monolith.model.ActivityType;
import com.homics.monolith.model.UserActivity;
import com.homics.monolith.repository.UserActivityRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserActivityService {

    private UserActivityRepository userActivityRepository;

    public UserActivityService(UserActivityRepository userActivityRepository) {
        this.userActivityRepository = userActivityRepository;
    }

    public void addLogin(String userName) {
        UserActivity userActivity = new UserActivity();
        userActivity.setUsername(userName);
        userActivity.setActivityDate(Instant.now());
        userActivity.setActivityType(ActivityType.LOGIN);
        userActivityRepository.save(userActivity);
    }

    public void addLogout(String userName) {
        UserActivity userActivity = new UserActivity();
        userActivity.setUsername(userName);
        userActivity.setActivityDate(Instant.now());
        userActivity.setActivityType(ActivityType.LOGOUT);
        userActivityRepository.save(userActivity);
    }

    public List<UserActivityDto> getUserActivities() {
        return userActivityRepository.findAll()
                .stream()
                .map(this::mapActivity)
                .collect(Collectors.toList());
    }

    private UserActivityDto mapActivity(UserActivity userActivity) {
        return new UserActivityDto(userActivity.getUsername(), userActivity.getActivityType(), userActivity.getActivityDate());
    }

}
