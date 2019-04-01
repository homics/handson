package com.homics.stats.controller;

import com.homics.stats.controller.dto.OrderPayedDto;
import com.homics.stats.controller.dto.OrderStatsDto;
import com.homics.stats.service.OrderStatsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("stats")
public class OrderStatsController {

    private OrderStatsService orderStatsService;

    public OrderStatsController(OrderStatsService orderStatsService) {
        this.orderStatsService = orderStatsService;
    }

    @PostMapping("/api/orders")
    public void addOrderStat(@RequestBody OrderPayedDto orderPayedDto) {
        orderStatsService.addOrder(orderPayedDto);
    }

    @GetMapping("/internal/orders")
    public OrderStatsDto getStats() {
        return orderStatsService.getStats();
    }
}
