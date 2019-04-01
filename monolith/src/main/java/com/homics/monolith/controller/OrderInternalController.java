package com.homics.monolith.controller;

import com.homics.monolith.controller.dto.ArticleDto;
import com.homics.monolith.model.Order;
import com.homics.monolith.service.OrderService;
import com.homics.monolith.service.PayOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mono/internal/orders")
public class OrderInternalController {

    private OrderService orderService;
    private PayOrderService payOrderService;

    public OrderInternalController(OrderService orderService, PayOrderService payOrderService) {
        this.orderService = orderService;
        this.payOrderService = payOrderService;
    }

    @GetMapping("/current")
    public Order getCurrentOrder() {
        return orderService.getCurrentOrCreateOrder();
    }

    @GetMapping("/history")
    public List<Order> getHistory() {
        return orderService.getPayedOrders();
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
        payOrderService.payOrder(orderId);
    }
}
