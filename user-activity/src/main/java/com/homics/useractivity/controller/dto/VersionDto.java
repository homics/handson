package com.homics.useractivity.controller.dto;

public class VersionDto {

    private final String name;
    private final String createdDate;
    private final String sendDate;

    public VersionDto(String name, String createdDate, String sendDate) {
        this.name = name;
        this.createdDate = createdDate;
        this.sendDate = sendDate;
    }

    public String getName() {
        return name;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getSendDate() {
        return sendDate;
    }
}
