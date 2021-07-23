package com.Visualizer.Stockopedia.Service.Kafka;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class kafkaProducerServiceImpl implements kafkaProducerService{

    private static final Logger logger = LoggerFactory.getLogger(kafkaProducerServiceImpl.class);

    private final KafkaTemplate<String,String> kafkaTemplate;

    public kafkaProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String message) {
        String TOPIC = "stock1";
        ListenableFuture<SendResult<String, String>> future
                = this.kafkaTemplate.send(TOPIC, message);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Sent message: " + message);
            }

            @Override
            public void onFailure(@NotNull Throwable ex) {
                logger.error("Unable to send message : " + message, ex);
            }
        });
    }
}
