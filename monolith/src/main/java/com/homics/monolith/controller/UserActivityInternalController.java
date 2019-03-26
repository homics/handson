package com.homics.monolith.controller;

import com.homics.monolith.controller.dto.UserActivityDto;
import com.homics.monolith.service.UserActivityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mono/internal/user")
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
