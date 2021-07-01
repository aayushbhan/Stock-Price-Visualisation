package com.Visualizer.Stockopedia.Service.Kafka;

import com.Visualizer.Stockopedia.Model.AlphaVantageTimeSeriesDailyJson;
import com.Visualizer.Stockopedia.Model.AlphaVantageTimeSeriesDailyJsonDaily;

public interface kafkaConsumerService {
    void consumer(String message);
    void consume(AlphaVantageTimeSeriesDailyJson alphaVantageTimeSeriesDailyJson);
}
