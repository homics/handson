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


    public void onImpactStockMessage() {
        // TODO 4
        //  Consume ImpactStockMessage from TOPIC_IMPACT_STOCK
        //  Call the service with the messages for treatment.
    }

}
