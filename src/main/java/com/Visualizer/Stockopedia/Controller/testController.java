package com.Visualizer.Stockopedia.Controller;

import com.Visualizer.Stockopedia.Service.Kafka.kafkaProducerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {

    private final kafkaProducerServiceImpl kafkaProducerService;

    public testController(kafkaProducerServiceImpl kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/publish")
    public void publishMessage(){
        kafkaProducerService.send("This is test message 1");
        kafkaProducerService.send("This is test message 2");
        kafkaProducerService.send("This is test message 3");
        kafkaProducerService.send("This is test message 4");
    }
}




