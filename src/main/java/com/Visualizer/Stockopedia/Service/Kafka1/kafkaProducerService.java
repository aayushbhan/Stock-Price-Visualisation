package com.Visualizer.Stockopedia.Service.Kafka1;

import com.Visualizer.Stockopedia.Model.AlphaVantageTimeSeriesDailyJson;

public interface kafkaProducerService {
    void send(String message);
    void sendTimeSeries(AlphaVantageTimeSeriesDailyJson alphaVantageTimeSeriesDailyJson);
}
