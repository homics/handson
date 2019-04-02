package com.homics.monolith.service;

import com.homics.messaging.model.OrderPayedMessage;
import com.homics.monolith.model.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static com.homics.messaging.config.KafkaTopicConfig.TOPIC_STATS;

@Service
public class StatsService {
    private KafkaTemplate<String, OrderPayedMessage> kafkaTemplate;

    public StatsService( KafkaTemplate<String, OrderPayedMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStat(Order order) {
        Message<OrderPayedMessage> message = MessageBuilder
                .withPayload(new OrderPayedMessage(order.getId(), order.getTotalPrice(), order.getUser()))
                .setHeader(KafkaHeaders.TOPIC, TOPIC_STATS)
                .build();

        kafkaTemplate.send(message);
    }
}
