package com.Visualizer.Stockopedia.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeSeriesModel {
    @Id
    List<AlphaVantageTimeSeriesDailyJsonMetaData> alphaVantageTimeSeriesDailyJsonDailyMetaData;

    List<AlphaVantageTimeSeriesDailyJsonDaily> alphaVantageTimeSeriesDailyJsonDailies;
}
