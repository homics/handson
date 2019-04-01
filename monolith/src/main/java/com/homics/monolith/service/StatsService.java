package com.homics.monolith.service;

import com.homics.monolith.model.Order;
import com.homics.monolith.model.OrderPayMessage;
import com.homics.monolith.repository.OrderStatsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.homics.monolith.controller.dto.OrderPayMessageDto;
import org.springframework.web.client.RestTemplate;

@Service
public class StatsService {
    final String ORDER_STAT_URL = "http://localhost:9002/stats/api/orders";
    private RestTemplate restTemplate;
    private OrderStatsRepository orderStatsRepository;

    public StatsService(RestTemplate restTemplate, OrderStatsRepository orderStatsRepository) {
        this.restTemplate = restTemplate;
        this.orderStatsRepository = orderStatsRepository;
    }

    public void addOrderPayMessage(Order order) {
        orderStatsRepository.save(new OrderPayMessage(order));
    }

    public void sendStats() {
        orderStatsRepository.findAll().forEach(this::sendStat);
    }

    private void sendStat(OrderPayMessage orderPayMessage) {
        Order order = orderPayMessage.getOrder();
        OrderPayMessageDto orderPayMessageDto = new OrderPayMessageDto(order.getId(), order.getTotalPrice(), order.getUser());

        ResponseEntity<String> response = restTemplate.postForEntity(ORDER_STAT_URL, orderPayMessageDto, String.class);

        if (HttpStatus.OK.equals(response.getStatusCode())) {
            orderStatsRepository.delete(orderPayMessage);
        }
    }
}
