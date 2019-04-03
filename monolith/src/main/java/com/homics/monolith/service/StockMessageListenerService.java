package com.homics.monolith.service;

import com.homics.messaging.model.StockAcknowledgmentMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static com.homics.messaging.config.KafkaConfig.GROUP_ID;
import static com.homics.messaging.config.KafkaConsumerConfig.STOCK_ACKNOWLEDGMENT_FACTORY;
import static com.homics.messaging.config.KafkaTopicConfig.TOPIC_ACKNOWLEDGE_STOCK;


@Service
public class StockMessageListenerService {

    private OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public StockMessageListenerService(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = TOPIC_ACKNOWLEDGE_STOCK, groupId = GROUP_ID, containerFactory = STOCK_ACKNOWLEDGMENT_FACTORY)
    public void onStockAcknowledgmentMessage(@Payload StockAcknowledgmentMessage message) {
        logger.info(String.format("#### -> Consuming StockAcknowledgmentMessage -> %s", message));
        orderService.updateOrderStatus(message);
    }
}
