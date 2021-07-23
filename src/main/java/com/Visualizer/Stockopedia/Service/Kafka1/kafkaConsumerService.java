package com.Visualizer.Stockopedia.Service.Kafka1;

import com.Visualizer.Stockopedia.Model.AlphaVantageTimeSeriesDailyJson;

public interface kafkaConsumerService {
    void consumer(String message);
    void consume(AlphaVantageTimeSeriesDailyJson alphaVantageTimeSeriesDailyJson);
}
