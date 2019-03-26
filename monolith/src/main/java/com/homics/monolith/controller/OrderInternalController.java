package com.homics.monolith.controller;

import com.homics.monolith.controller.dto.ArticleDto;
import com.homics.monolith.controller.dto.OrderStatsDto;
import com.homics.monolith.model.Order;
import com.homics.monolith.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mono/internal/orders")
public class OrderInternalController {

    private OrderService orderService;

    public OrderInternalController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/current")
    public Order getCurrentOrder() {
        return orderService.getCurrentOrCreateOrder();
    }

    @GetMapping("/history")
    public List<Order> getHistory() {
        return orderService.getPayedOrders();
    }

    @GetMapping("/stats")
    public OrderStatsDto getStats() {
        return orderService.getStats();
    }

    @PostMapping("/{orderId}/add-article")
    public void addArticle(@PathVariable Long orderId, @RequestBody ArticleDto articleDto) {
        orderService.addArticle(orderId, articleDto.getArticleId(), articleDto.getQuantity());
    }

    @DeleteMapping("/{orderId}/order-lines/{orderLineId}")
    public void removeArticle(@PathVariable Long orderId, @PathVariable Long orderLineId) {
        orderService.removeOrderLine(orderId, orderLineId);
    }

    @PostMapping("/{orderId}/pay")
    public void payOrder(@PathVariable Long orderId) {
        orderService.payOrder(orderId);
    }
}
