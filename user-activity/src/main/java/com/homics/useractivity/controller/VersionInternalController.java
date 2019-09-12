package com.homics.useractivity.controller;

import com.homics.useractivity.controller.dto.VersionDto;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.ZoneOffset;

@RestController
@RequestMapping("/user/internal/version")
public class VersionInternalController {

    private final BuildProperties buildProperties;

    public VersionInternalController(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @GetMapping
    public VersionDto version() {
        Instant time = buildProperties.getTime();
        return new VersionDto(buildProperties.getVersion(), getFormattedDate(time), getFormattedDate(Instant.now()));
    }

    private String getFormattedDate(Instant time) {
        int hour = time.atZone(ZoneOffset.UTC).getHour();
        int minute = time.atZone(ZoneOffset.UTC).getMinute();
        int second = time.atZone(ZoneOffset.UTC).getSecond();
        return hour + ":" + minute + ":" + second;
    }

}
