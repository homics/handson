package com.homics.stats.service;

import com.homics.messaging.model.OrderPayedMessage;
import com.homics.stats.config.UserStore;
import com.homics.stats.controller.dto.OrderStatsDto;
import com.homics.stats.model.OrderStats;
import com.homics.stats.repository.OrderStatsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static com.homics.messaging.config.KafkaConfig.GROUP_ID;
import static com.homics.messaging.config.KafkaConsumerConfig.IMPACT_STOCK_MESSAGE_FACTORY;
import static com.homics.messaging.config.KafkaTopicConfig.TOPIC_STATS;


@Service
public class OrderStatsService {

    private OrderStatsRepository orderStatsRepository;
    private final Logger logger = LoggerFactory.getLogger(OrderStatsService.class);

    public OrderStatsService(OrderStatsRepository orderStatsRepository) {
        this.orderStatsRepository = orderStatsRepository;
    }

    @KafkaListener(topics = TOPIC_STATS, groupId = GROUP_ID, containerFactory = IMPACT_STOCK_MESSAGE_FACTORY)
    public void onImpactStockMessage(@Payload OrderPayedMessage orderPayedMessage) {
        logger.info(String.format("#### -> Consuming OrderPayedMessage -> %s", orderPayedMessage));
        orderStatsRepository.save(new OrderStats(orderPayedMessage.getOrderId(), orderPayedMessage.getOrderPrice(), orderPayedMessage.getUser()));
    }

    public OrderStatsDto getStats() {
        String user = UserStore.getUserName();
        Long count = orderStatsRepository.getOrderCount(user);
        Double avg = orderStatsRepository.getOrderAvg(user);
        return new OrderStatsDto(count, avg);
    }
}
