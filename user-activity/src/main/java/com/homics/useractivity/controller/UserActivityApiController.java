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

    private void registerActivity() {
        // TODO 1:
        //  Listen to post query on /user/api/activity.
        //  Get the object from the request body and register it with the UserActivityService
        //  Use @PostMapping and @RequestBody
        //  Warning: you already have the /user/api in your RequestMapping
    }
}
