package com.Visualizer.Stockopedia.Service.AlphaVantage;

import com.Visualizer.Stockopedia.Model.AlphaVantageTimeSeriesDailyJson;
import com.Visualizer.Stockopedia.Model.TimeSeriesModel;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

@Service
public class TimeSeriesService {


    Logger logger = LoggerFactory.getLogger(TimeSeriesService.class) ;

    private final RestTemplate restTemplate;
    private final TimeSeriesModel timeSeriesModel;
    private KafkaTemplate<String, AlphaVantageTimeSeriesDailyJson> stockkafkaTemplate;
    private final static String TOPIC = "stock1";

    public TimeSeriesService(RestTemplateBuilder restTemplateBuilder, TimeSeriesModel timeSeriesModel) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(500))
                .setReadTimeout(Duration.ofSeconds(500))
                .build();
        this.timeSeriesModel = timeSeriesModel;
    }

    public AlphaVantageTimeSeriesDailyJson getPostsPlainJSON() throws IOException {
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=8G9V07W6ZSVXKTNF";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        String givenData = Objects.requireNonNull(this.restTemplate.getForObject(url, String.class));
        //return "success";
        return objectMapper.readValue(givenData,AlphaVantageTimeSeriesDailyJson.class);
    }

    public AlphaVantageTimeSeriesDailyJson getUserId() throws IOException {
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=8G9V07W6ZSVXKTNF";
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);

        // this.stockkafkaTemplate.send(TOPIC, alphaVantageTimeSeriesDailyJson);
        return objectMapper.readValue(response.getBody(),AlphaVantageTimeSeriesDailyJson.class);
    }

}


// https://stackoverflow.com/questions/39199301/how-to-create-a-state-store-with-hashmap-as-value-in-kafka-streams
//https://www.geeksforgeeks.org/spring-boot-how-to-publish-json-messages-on-apache-kafka/