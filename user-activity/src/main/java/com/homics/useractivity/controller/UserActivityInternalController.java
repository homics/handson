package com.homics.useractivity.controller;

import com.homics.useractivity.controller.dto.UserActivityDto;
import com.homics.useractivity.service.UserActivityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/internal")
public class UserActivityInternalController {

    private UserActivityService userActivityService;

    public UserActivityInternalController(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @GetMapping("/activity")
    private List<UserActivityDto> getActivities() {
        return userActivityService.getUserActivities();
    }

}
