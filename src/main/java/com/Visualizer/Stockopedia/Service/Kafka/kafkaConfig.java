package com.Visualizer.Stockopedia.Service.Kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class kafkaConfig {

    private static final String servers = "localhost:9092";

    @Bean
    public KafkaAdmin admin(){
        Map<String,Object> kafkaConfigs = new HashMap<>();

        kafkaConfigs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,servers);

        return new KafkaAdmin(kafkaConfigs);
    }
}
