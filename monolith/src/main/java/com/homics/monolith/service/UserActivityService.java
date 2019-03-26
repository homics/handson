package com.homics.monolith.service;

import com.homics.monolith.controller.dto.UserActivityDto;
import com.homics.monolith.model.ActivityType;
import com.homics.monolith.model.UserActivity;
import com.homics.monolith.repository.UserActivityRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserActivityService {
    // TODO 5:
    //  Clean the code in monolith
    //    - remove userActivityRepository
    //    - remove the getUserActivities() and the mapActivity

    private UserActivityRepository userActivityRepository;

    private RestTemplate restTemplate;

    public UserActivityService(UserActivityRepository userActivityRepository, RestTemplate restTemplate) {
        this.userActivityRepository = userActivityRepository;
        this.restTemplate = restTemplate;
    }

    public void addLogin(String userName) {
        // TODO 4:
        //  Change UserActivity into UserActivityDto
        UserActivity userActivity = new UserActivity();
        userActivity.setUsername(userName);
        userActivity.setActivityDate(Instant.now());
        userActivity.setActivityType(ActivityType.LOGIN);
        // TODO 4:
        //  Remove save and use the post method that you just implemented
        userActivityRepository.save(userActivity);
    }

    public void addLogout(String userName) {
        // TODO 4:
        //  Change UserActivity into UserActivityDto
        UserActivity userActivity = new UserActivity();
        userActivity.setUsername(userName);
        userActivity.setActivityDate(Instant.now());
        userActivity.setActivityType(ActivityType.LOGOUT);
        // TODO 4:
        //  Remove save and use the post method that you just implemented
        userActivityRepository.save(userActivity);
    }

    public void post(UserActivityDto userActivityDto) {
        // TODO 3:
        //  Change the function below to call the right url with the right object
        //  Remember that the url is on localhost:9001 and you used it to add an activity
        //  The object you are sending is a UserActivityDto
        restTemplate.postForEntity("MY_URL", "MY_OBJECT", Void.class);
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
