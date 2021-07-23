package com.Visualizer.Stockopedia.Controller;

import com.Visualizer.Stockopedia.Model.AlphaVantageTimeSeriesDailyJson;
import com.Visualizer.Stockopedia.Service.AlphaVantage.TimeSeriesService;
import com.Visualizer.Stockopedia.Service.Kafka1.kafkaProducerServiceImpl;
import com.Visualizer.Stockopedia.Service.Spark.SharePriceService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1")
public class testController {

    private final kafkaProducerServiceImpl kafkaProducerService;
    private final SharePriceService sharePriceService;
    private final TimeSeriesService restService;
    private final KafkaTemplate<String, AlphaVantageTimeSeriesDailyJson> userKafkaTemplate;

    public testController(kafkaProducerServiceImpl kafkaProducerService, SharePriceService sharePriceService, TimeSeriesService restService, KafkaTemplate<String, AlphaVantageTimeSeriesDailyJson> userKafkaTemplate) {
        this.kafkaProducerService = kafkaProducerService;
        this.sharePriceService = sharePriceService;
        this.restService = restService;
        this.userKafkaTemplate = userKafkaTemplate;
    }


    @GetMapping("/publish")
    public void publishMessage() {
        kafkaProducerService.send("This is test message 1");
        kafkaProducerService.send("This is test message 2");
        kafkaProducerService.send("This is test message 3");
        kafkaProducerService.send("This is test message 4");
        try {
            this.kafkaProducerService.sendTimeSeries(restService.streamStockData());
            //sharePriceService.SparkStream();
        } catch (IOException e) {
            System.out.println("**********************AANKH HUI NAM******************************");
            e.printStackTrace();
        }
    }

    @GetMapping("/streaming/{symbol}")
    public void realTimeStreaming(@PathVariable("symbol")String symbol) throws InterruptedException, IOException {
        sharePriceService.SparkStream(symbol);
    }

    @GetMapping("/news")
    public String getnewsaapl() {
        try {
            restService.getnews();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}

