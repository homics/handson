package com.homics.monolith.service;

import com.homics.messaging.model.StockAcknowledgmentMessage;
import com.homics.monolith.config.UserStore;
import com.homics.monolith.model.Article;
import com.homics.monolith.model.Order;
import com.homics.monolith.model.OrderLine;
import com.homics.monolith.model.OrderStatus;
import com.homics.monolith.repository.OrderLineRepository;
import com.homics.monolith.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderLineRepository orderLineRepository;
    private ArticleService articleService;
    private StatsService statsService;
    private StockService stockService;

    public OrderService(OrderRepository orderRepository, OrderLineRepository orderLineRepository, ArticleService articleService,
                        StockService stockService, StatsService statsService) {
        this.articleService = articleService;
        this.stockService = stockService;
        this.statsService = statsService;
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
    }

    public List<Order> getPayedOrCancelledOrders() {
        return orderRepository.getPayedOrCancelledOrder(UserStore.getUserName());
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    private Order getCurrentOrder() {
        return orderRepository.getCurrentOrder(UserStore.getUserName());
    }

    public Order getCurrentOrCreateOrder() {
        Order order = getCurrentOrder();
        if (order == null) {
            order = new Order();
            order.setUser(UserStore.getUserName());
            order.setStatus(OrderStatus.PENDING);
            order = orderRepository.save(order);
        }
        return order;
    }

    public void addArticle(Long orderId, Long articleId, Integer quantity) {
        Order order = getOrderById(orderId);
        Optional<OrderLine> orderLine = getOrderLineForArticle(articleId, order);
        if (orderLine.isPresent()) {
            OrderLine line = orderLine.get();
            line.setQuantity(line.getQuantity() + quantity);
            orderLineRepository.save(line);
        } else {
            OrderLine line = new OrderLine();
            Article article = articleService.getArticleById(articleId);
            line.setQuantity(quantity);
            line.setArticle(article);
            orderLineRepository.save(line);
            order.getOrderLines().add(line);
        }
        refreshTotalPrice(order);
        orderRepository.save(order);
    }

    private Optional<OrderLine> getOrderLineForArticle(Long articleId, Order order) {
        return order.getOrderLines().stream().filter(l -> articleId.equals(l.getArticle().getId())).findFirst();
    }

    @Transactional
    public void payOrder(Order order) {
        stockService.impactStock(order);
    }

    public void removeOrderLine(Long orderId, Long orderLineId) {
        Order order = getOrderById(orderId);
        Optional<OrderLine> orderLine = order.getOrderLines().stream().filter(line -> line.getId().equals(orderLineId)).findFirst();
        orderLine.ifPresent(orderLine1 -> order.getOrderLines().remove(orderLine1));
        refreshTotalPrice(order);
        orderRepository.save(order);
    }

    private void refreshTotalPrice(Order order) {
        order.setTotalPrice(order.getOrderLines().stream().mapToDouble(line -> line.getArticle().getPrice() * line.getQuantity()).sum());
    }

    @Transactional
    public void updateOrderStatus(StockAcknowledgmentMessage message) {
        Order order = orderRepository.getOne(message.getOperationId());
        if (message.isSucceed()) {
            order.setStatus(OrderStatus.PAYED);
            orderRepository.save(order);
            statsService.sendStat(order);
        } else {
            order.setStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);
        }
    }
}
