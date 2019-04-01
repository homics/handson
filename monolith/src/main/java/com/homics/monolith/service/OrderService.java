package com.homics.monolith.service;

import com.homics.monolith.config.UserStore;
import com.homics.monolith.controller.dto.OrderStatsDto;
import com.homics.monolith.model.Article;
import com.homics.monolith.model.Order;
import com.homics.monolith.model.OrderLine;
import com.homics.monolith.model.OrderStatus;
import com.homics.monolith.repository.ArticleRepository;
import com.homics.monolith.repository.OrderLineRepository;
import com.homics.monolith.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private ArticleRepository articleRepository;
    private OrderLineRepository orderLineRepository;
    private ArticleService articleService;

    public OrderService(OrderRepository orderRepository, OrderLineRepository orderLineRepository, ArticleService articleService, ArticleRepository articleRepository) {
        this.articleService = articleService;
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
        this.articleRepository = articleRepository;
    }

    public List<Order> getPayedOrders() {
        return orderRepository.getPayedOrder(UserStore.getUserName());
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
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
        validateOrderStock(order);
        order.setStatus(OrderStatus.PAYED);
        orderRepository.save(order);
        impactArticleStock(order);
    }

    private void impactArticleStock(Order order) {
        order.getOrderLines().forEach(orderLine -> impactArticleStock(orderLine.getArticle(), orderLine.getQuantity()));
    }

    private void impactArticleStock(Article article, Integer quantity) {
        articleRepository.decrementStock(article.getId(), quantity);
    }

    private void validateOrderStock(Order order) {
        order.getOrderLines().forEach(line -> {
            if (line.getQuantity() > line.getArticle().getStock()) {
                throw new ValidationException("The stock is no longer available");
            }
        });
    }

    // TODO 4
    //  Clean this method, it won't be use anymore.
    public OrderStatsDto getStats() {
        String user = UserStore.getUserName();
        Long count = orderRepository.getOrderCount(user);
        Double avg = orderRepository.getOrderAvg(user);
        return new OrderStatsDto(count, avg);
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
}
