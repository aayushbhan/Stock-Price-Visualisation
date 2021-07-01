package com.Visualizer.Stockopedia.Service.Kafka;

import com.Visualizer.Stockopedia.Model.AlphaVantageTimeSeriesDailyJson;
import com.Visualizer.Stockopedia.Model.AlphaVantageTimeSeriesDailyJsonDaily;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class kafkaConsumerServiceImpl implements kafkaConsumerService{

    private final Logger logger = LoggerFactory.getLogger(kafkaConsumerService.class);

    @Override
    @KafkaListener(
            topics = "stock1",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumer(String message) {
        logger.info("Message received ->"+message);
    }

    //@Override
    @KafkaListener(topics = "stock1",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "userKafkaListenerContainerFactory")
    public void consume(AlphaVantageTimeSeriesDailyJson alphaVantageTimeSeriesDailyJson) {
        logger.info(String.format("AlphaVantageTimeSeriesDaily created -> %s", alphaVantageTimeSeriesDailyJson));
    }
}
