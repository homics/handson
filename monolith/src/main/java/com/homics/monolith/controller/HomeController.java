package com.homics.monolith.controller;

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

    @GetMapping(value = {
            "/articles",
            "/cart",
            "/history"})
    public String index() {
        return "/index.html";
    }

}
