package com.homics.monolith.controller;

import com.homics.monolith.service.AuthenticationFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller only renders the UI of the application.
 * It's the entry point for the front of the application.
 * Java delegates the rendering to React.
 */
@Controller
@RequestMapping("/mono")
public class HomeController {

    private static final String MONOLITH_URI = "/mono/articles";

    private AuthenticationFacade authenticationFacade;

    public HomeController(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping("/login")
    public String login() {
        if (authenticationFacade.isAnonymous()) {
            return "/index.html";
        }

        return "redirect:" + MONOLITH_URI;
    }

    @GetMapping(value = {
            "/articles",
            "/cart",
            "/history",
            "/stats",
            "/userActivity"})
    public String index() {
        return "/index.html";
    }

}
