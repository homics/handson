package com.homics.monolith.service;

import com.homics.messaging.model.ArticleStockDto;
import com.homics.messaging.model.ImpactStockMessage;
import com.homics.monolith.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.homics.messaging.config.KafkaTopicConfig.TOPIC_IMPACT_STOCK;


@Service
public class StockService {

    private KafkaTemplate<String, ImpactStockMessage> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(StockService.class);

    public StockService(KafkaTemplate<String, ImpactStockMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void impactStock(Order order) {
        List<ArticleStockDto> articleStocks = order.getOrderLines().stream()
                .map(line -> new ArticleStockDto(line.getArticle().getId(), -line.getQuantity().longValue()))
                .collect(Collectors.toList());

        ImpactStockMessage impactStockMessage = new ImpactStockMessage(
                order.getId(),
                articleStocks
        );

        logger.info(String.format("#### -> Sending ImpactStockMessage -> %s", impactStockMessage));

        Message<ImpactStockMessage> message = MessageBuilder
                .withPayload(impactStockMessage)
                .setHeader(KafkaHeaders.TOPIC, TOPIC_IMPACT_STOCK)
                .build();

        kafkaTemplate.send(message);
    }
}
