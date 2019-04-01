package com.homics.stats.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderStats {

    @Id
    private Long orderId;
    private Double orderPrice;
    private String user;

    public OrderStats() {
    }

    public OrderStats(Long orderId, Double orderPrice, String user) {
        this.orderId = orderId;
        this.orderPrice = orderPrice;
        this.user = user;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
