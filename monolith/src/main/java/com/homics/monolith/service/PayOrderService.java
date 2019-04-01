package com.homics.monolith.service;

import com.homics.monolith.model.Order;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PayOrderService {
    private StatsService statsService;
    private OrderService orderService;

    public PayOrderService(StatsService statsService, OrderService orderService) {
        this.statsService = statsService;
        this.orderService = orderService;
    }

    @Transactional
    public void payOrder(Long orderId) {
        Order order = orderService.getOrderById(orderId);
        orderService.payOrder(order);
        statsService.addOrderPayMessage(order);
    }
}
