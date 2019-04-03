package com.homics.stock.service;


import com.homics.messaging.model.ImpactStockMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import static com.homics.messaging.config.KafkaConfig.GROUP_ID;
import static com.homics.messaging.config.KafkaConsumerConfig.IMPACT_STOCK_MESSAGE_FACTORY;
import static com.homics.messaging.config.KafkaTopicConfig.TOPIC_IMPACT_STOCK;

@Service
public class ImpactStockConsumer {

    private final Logger logger = LoggerFactory.getLogger(StockService.class);
    private StockService stockService;

    public ImpactStockConsumer(StockService stockService) {
        this.stockService = stockService;
    }


    @KafkaListener(topics = TOPIC_IMPACT_STOCK, groupId = GROUP_ID, containerFactory = IMPACT_STOCK_MESSAGE_FACTORY)
    public void onImpactStockMessage(@Payload ImpactStockMessage impactStockMessage) {
        logger.info(String.format("#### -> Consuming ImpactStockMessage -> %s", impactStockMessage));
        stockService.impactStock(impactStockMessage);
    }

}
