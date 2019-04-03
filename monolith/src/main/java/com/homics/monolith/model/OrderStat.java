package com.homics.monolith.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class OrderStat {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Order order;

    public OrderStat() {
    }

    public OrderStat(Order order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
