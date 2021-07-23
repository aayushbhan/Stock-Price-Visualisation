package com.Visualizer.Stockopedia.config.kafkaConfigs1;

import com.Visualizer.Stockopedia.Model.AlphaVantageTimeSeriesDailyJson;
import com.Visualizer.Stockopedia.Service.AlphaVantage.JsonPOJOSerializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class kafkaProducerConfig {
    @Bean
    public ProducerFactory<String,String> producerFactory(){
        return  new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String,Object> producerConfigs(){
        Map<String,Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return props;
    }

    @Bean
    public KafkaTemplate<String,String> kafkaTemplate() {
        return  new KafkaTemplate<>(producerFactory());
    }

    //2. Send User objects to Kafka
    @Bean
    public ProducerFactory<String, AlphaVantageTimeSeriesDailyJson> userProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonPOJOSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, AlphaVantageTimeSeriesDailyJson> userKafkaTemplate() {
        return new KafkaTemplate<>(userProducerFactory());
    }
}
