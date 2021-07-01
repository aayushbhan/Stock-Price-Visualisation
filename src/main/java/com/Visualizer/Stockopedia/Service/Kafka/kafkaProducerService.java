package com.Visualizer.Stockopedia.Service.Kafka;

import com.Visualizer.Stockopedia.Model.AlphaVantageTimeSeriesDailyJson;

public interface kafkaProducerService {
    void send(String message);
    void sendTimeSeries(AlphaVantageTimeSeriesDailyJson alphaVantageTimeSeriesDailyJson);
}
