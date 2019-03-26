package com.homics.useractivity.service;

import com.homics.useractivity.controller.dto.UserActivityDto;
import com.homics.useractivity.model.UserActivity;
import com.homics.useractivity.repository.UserActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserActivityService {

    private final UserActivityRepository userActivityRepository;

    public UserActivityService(UserActivityRepository userActivityRepository) {
        this.userActivityRepository = userActivityRepository;
    }

    public List<UserActivityDto> getUserActivities() {
        return userActivityRepository.findAll()
                .stream()
                .map(this::mapActivity)
                .collect(Collectors.toList());
    }

    public void registerActivity(UserActivityDto userActivityDto) {
        UserActivity userActivity = new UserActivity();
        userActivity.setUsername(userActivityDto.getUsername());
        userActivity.setActivityDate(userActivityDto.getActivityDate());
        userActivity.setActivityType(userActivityDto.getActivityType());
        userActivityRepository.save(userActivity);
    }

    private UserActivityDto mapActivity(UserActivity userActivity) {
        return new UserActivityDto(userActivity.getUsername(), userActivity.getActivityType(), userActivity.getActivityDate());
    }
}
