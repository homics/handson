package com.homics.stock.config;

import com.homics.messaging.config.KafkaConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({KafkaConfig.class})
public class MessagingConfig {
}
