package com.homics.monolith.controller;

import com.homics.monolith.controller.dto.CurrentUserDto;
import com.homics.monolith.service.AuthenticationFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mono/user")
public class AuthenticationInternalController {

    private final AuthenticationFacade authenticationFacade;

    public AuthenticationInternalController(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping("/current")
    public CurrentUserDto getCurrentUser() {
        return new CurrentUserDto(authenticationFacade.getLoggedUserName());
    }
}
