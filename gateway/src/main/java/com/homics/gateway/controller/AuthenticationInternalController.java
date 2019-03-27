package com.homics.gateway.controller;

import com.homics.gateway.controller.dto.CurrentUserDto;
import com.homics.gateway.service.AuthenticationFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/user")
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
