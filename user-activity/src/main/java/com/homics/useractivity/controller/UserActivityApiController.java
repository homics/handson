package com.homics.useractivity.controller;

import com.homics.useractivity.controller.dto.UserActivityDto;
import com.homics.useractivity.service.UserActivityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/api")
public class UserActivityApiController {

    private UserActivityService userActivityService;

    public UserActivityApiController(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @PostMapping("/activity")
    private void registerActivity(@RequestBody UserActivityDto userActivityDto) {
        userActivityService.registerActivity(userActivityDto);
    }
}
