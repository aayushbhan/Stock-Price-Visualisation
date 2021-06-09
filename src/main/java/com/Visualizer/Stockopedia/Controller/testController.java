package com.Visualizer.Stockopedia.Controller;

import com.Visualizer.Stockopedia.Service.Kafka.kafkaProducerServiceImpl;
import com.Visualizer.Stockopedia.Service.Spark.StockValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {

    private final kafkaProducerServiceImpl kafkaProducerService;
    private final StockValue stockValue;

    public testController(kafkaProducerServiceImpl kafkaProducerService, StockValue stockValue) {
        this.kafkaProducerService = kafkaProducerService;
        this.stockValue = stockValue;
    }


    @GetMapping("/publish")
    public void publishMessage(){
        kafkaProducerService.send("This is test message 1");
        kafkaProducerService.send("This is test message 2");
        kafkaProducerService.send("This is test message 3");
        kafkaProducerService.send("This is test message 4");
        try {
            stockValue.SparkStream();
        } catch (InterruptedException e) {
            System.out.println("**********************AANKH HUI NAM******************************");
            e.printStackTrace();
        }
    }
}




