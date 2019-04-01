package com.homics.monolith.controller.dto;

public class OrderPayMessageDto {
    private Long orderId;
    private Double orderPrice;
    private String user;

    public OrderPayMessageDto() {
    }

    public OrderPayMessageDto(Long orderId, Double orderPrice, String user) {
        this.orderPrice = orderPrice;
        this.user = user;
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }
}
