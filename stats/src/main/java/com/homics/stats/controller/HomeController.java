package com.homics.stats.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stats")
public class HomeController {

    @GetMapping(value = {"/stat-order"})
    public String index() {
        return "/index.html";
    }
}
