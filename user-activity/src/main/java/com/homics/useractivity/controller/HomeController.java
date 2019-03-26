package com.homics.useractivity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller only renders the UI of the application.
 * It's the entry point for the front of the application.
 * Java delegates the rendering to React.
 */
@Controller
@RequestMapping("/user")
public class HomeController {

    @GetMapping("/userActivity")
    public String index() {
        return "/index.html";
    }
}
