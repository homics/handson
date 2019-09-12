package com.homics.useractivity.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping({"/userActivity", "/versions"})
    public String index() {
        return "/index.html";
    }

    @GetMapping("/crash")
    public void crash() {
        logger.info("it's a crash!!");
        System.exit(0);
    }
}
