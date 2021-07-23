package com.Visualizer.Stockopedia.Service.AlphaVantage;

import com.Visualizer.Stockopedia.Model.AlphaVantageTimeSeriesDailyJsonDaily;

import java.util.List;
import java.util.Map;

public class TImeSeriesResponse {

        private final Map<String, String> metaData;
        private final List<AlphaVantageTimeSeriesDailyJsonDaily> stockData;

    TImeSeriesResponse(final Map<String, String> metaData, final List<AlphaVantageTimeSeriesDailyJsonDaily> stockData) {
            this.stockData = stockData;
            this.metaData = metaData;
        }

        public Map<String, String> getMetaData() {
            return metaData;
        }

        public List<AlphaVantageTimeSeriesDailyJsonDaily> getStockData() {
            return stockData;
        }
}


