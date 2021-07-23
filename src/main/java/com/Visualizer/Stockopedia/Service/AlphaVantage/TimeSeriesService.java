package com.Visualizer.Stockopedia.Service.AlphaVantage;

import com.Visualizer.Stockopedia.Model.AlphaVantageTimeSeriesDailyJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

@Service
public class TimeSeriesService {


    Logger logger = LoggerFactory.getLogger(TimeSeriesService.class) ;

    private final RestTemplate restTemplate;
/*
    private final TimeSeriesModel timeSeriesModel;
*/
    //private KafkaTemplate<String, AlphaVantageTimeSeriesDailyJson> stockkafkaTemplate;

    public TimeSeriesService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(500))
                .setReadTimeout(Duration.ofSeconds(500))
                .build();
       // this.timeSeriesModel = timeSeriesModel;
    }


    // News API
    public void getnews() {
        String url = "https://newsapi.org/v2/everything?q=apple&from=2021-07-03&to=2021-07-03&sortBy=popularity&apiKey=bccdd2a8450c4a6b834e435d585e6b1e";
        logger.info("news");
        new ObjectMapper();
        Objects.requireNonNull(this.restTemplate.getForObject(url, String.class));

    }
    //Stock Streaming Service function
    public AlphaVantageTimeSeriesDailyJson streamStockData() throws IOException {
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=8G9V07W6ZSVXKTNF";
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
        return objectMapper.readValue(response.getBody(),AlphaVantageTimeSeriesDailyJson.class);
    }

}
//https://fmpcloud.io/documentation#realtimeQuote
//https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=IBM&apikey=demo&datatype=csv
//https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey=demo&datatype=csv
//https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=demo&datatype=csv
// https://stackoverflow.com/questions/39199301/how-to-create-a-state-store-with-hashmap-as-value-in-kafka-streams
//https://www.geeksforgeeks.org/spring-boot-how-to-publish-json-messages-on-apache-kafka/