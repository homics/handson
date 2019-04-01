package com.homics.stats.service;

import com.homics.stats.config.UserStore;
import com.homics.stats.controller.dto.OrderPayedDto;
import com.homics.stats.controller.dto.OrderStatsDto;
import com.homics.stats.repository.OrderStatsRepository;
import com.homics.stats.model.OrderStats;
import org.springframework.stereotype.Service;


@Service
public class OrderStatsService {

    private OrderStatsRepository orderStatsRepository;

    public OrderStatsService(OrderStatsRepository orderStatsRepository) {
        this.orderStatsRepository = orderStatsRepository;
    }

    public void addOrder(OrderPayedDto orderPayedDto) {
        orderStatsRepository.save(new OrderStats(orderPayedDto.getOrderId(), orderPayedDto.getOrderPrice(), orderPayedDto.getUser()));
    }

    public OrderStatsDto getStats() {
        String user = UserStore.getUserName();
        Long count = orderStatsRepository.getOrderCount(user);
        Double avg = orderStatsRepository.getOrderAvg(user);
        return new OrderStatsDto(count, avg);
    }
}
