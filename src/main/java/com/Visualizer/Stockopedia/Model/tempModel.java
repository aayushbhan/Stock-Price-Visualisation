package com.Visualizer.Stockopedia.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class tempModel {
        @JsonProperty("Meta Data")
        String metaData;

        @JsonProperty("Time Series (5min)")
        String timeSeries;
}