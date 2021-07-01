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
//"Meta Data": {
//https://github.com/cclow/hexademo/tree/master/src/main/java/com/example/hexademo/alphavantage
//        "1. Information": "Intraday (5min) open, high, low, close prices and volume",
//        "2. Symbol": "IBM",
//        "3. Last Refreshed": "2021-06-29 18:15:00",
//        "4. Interval": "5min",
//        "5. Output Size": "Compact",
//        "6. Time Zone": "US/Eastern"
//    },
//    "Time Series (5min)": {
//        "2021-06-29 18:15:00": {
//            "1. open": "145.7900",
//            "2. high": "145.7900",
//            "3. low": "145.7900",
//            "4. close": "145.7900",
//            "5. volume": "1000"
//        },
//        "2021-06-29 17:25:00": {
//            "1. open": "145.5500",
//            "2. high": "145.5500",
//            "3. low": "145.5500",
//            "4. close": "145.5500",
//            "5. volume": "140"
//        },
//        "2021-06-29 16:10:00": {