package com.Visualizer.Stockopedia.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class kafkaConfig {

    private static final Logger logger = LoggerFactory.getLogger(kafkaConfig.class);

    private static final String servers = "localhost:9092";

    @Bean
    public KafkaAdmin admin(){
        Map<String,Object> kafkaConfigs = new HashMap<>();

        kafkaConfigs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,servers);

        return new KafkaAdmin(kafkaConfigs);
    }

    @Bean
    public NewTopic stock1(){
        logger.info("Created topic stock1");
        return TopicBuilder.name("stock1")
                .build();
    }
}
