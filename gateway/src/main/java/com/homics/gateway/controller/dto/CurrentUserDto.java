package com.homics.gateway.controller.dto;

public class CurrentUserDto {

    private final String userName;

    public CurrentUserDto(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
