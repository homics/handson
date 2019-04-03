package com.homics.stock.service;


import com.homics.messaging.model.ImpactStockMessage;
import com.homics.messaging.model.StockAcknowledgmentMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static com.homics.messaging.config.KafkaTopicConfig.TOPIC_ACKNOWLEDGE_STOCK;

@Service
public class StockAcknowledgmentProducer {

    private final Logger logger = LoggerFactory.getLogger(StockService.class);
    private KafkaTemplate<String, ImpactStockMessage> kafkaTemplate;

    public StockAcknowledgmentProducer(KafkaTemplate<String, ImpactStockMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void notifyStockChanges(Long operationId, boolean success) {
        logger.info(String.format("#### -> Sending StockAcknowledgmentMessage success: %s", success));
        Message<StockAcknowledgmentMessage> message = MessageBuilder
                .withPayload(new StockAcknowledgmentMessage(operationId, success))
                .setHeader(KafkaHeaders.TOPIC, TOPIC_ACKNOWLEDGE_STOCK)
                .build();

        kafkaTemplate.send(message);
    }
}
