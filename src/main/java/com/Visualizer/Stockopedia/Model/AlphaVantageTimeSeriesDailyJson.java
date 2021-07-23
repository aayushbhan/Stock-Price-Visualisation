package com.Visualizer.Stockopedia.Model;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlphaVantageTimeSeriesDailyJson {
    @JsonProperty("Meta Data")
    private AlphaVantageTimeSeriesDailyJsonMetaData metaData;
    @JsonProperty("Time Series (5min)")
    private Map<String, AlphaVantageTimeSeriesDailyJsonDaily> daily;
}
