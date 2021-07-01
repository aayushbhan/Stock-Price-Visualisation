package com.Visualizer.Stockopedia.Controller;

import com.Visualizer.Stockopedia.Model.AlphaVantageTimeSeriesDailyJson;
import com.Visualizer.Stockopedia.Model.AlphaVantageTimeSeriesDailyJsonDaily;
import com.Visualizer.Stockopedia.Service.AlphaVantage.TimeSeriesService;
import com.Visualizer.Stockopedia.Service.Kafka.kafkaProducerServiceImpl;
import com.Visualizer.Stockopedia.Service.Spark.StockValue;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class testController {

    private final kafkaProducerServiceImpl kafkaProducerService;
    private final StockValue stockValue;
    private final TimeSeriesService restService;
    private final KafkaTemplate<String, AlphaVantageTimeSeriesDailyJson> userKafkaTemplate;

    public testController(kafkaProducerServiceImpl kafkaProducerService, StockValue stockValue, TimeSeriesService restService,KafkaTemplate<String, AlphaVantageTimeSeriesDailyJson> userKafkaTemplate) {
        this.kafkaProducerService = kafkaProducerService;
        this.stockValue = stockValue;
        this.restService = restService;
        this.userKafkaTemplate = userKafkaTemplate;
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
    @GetMapping("/AAPL")
    public AlphaVantageTimeSeriesDailyJson getStaticdata() throws IOException {
        return  restService.getPostsPlainJSON();
    }
    @GetMapping("/publish1")
    public String getTimeSeries() throws IOException {

        this.kafkaProducerService.sendTimeSeries(restService.getUserId());
        return "Published Successfully";
    }
}




