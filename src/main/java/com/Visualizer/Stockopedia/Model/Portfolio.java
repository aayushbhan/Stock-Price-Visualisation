package com.Visualizer.Stockopedia.Model;

import lombok.Data;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;

@Document(collection = "Portfolio")
@Data
public class Portfolio {

    @Id
    private String portfolioId;

    private String userId;

    private Double totalInvestedValue;

    private Double totalCurrentValue;

    private ArrayList<Stocks> stocks;

    public Portfolio(String userId, Double totalInvestedValue, Double totalCurrentValue, ArrayList<Stocks> stocks) {
        this.userId = userId;
        this.totalInvestedValue = totalInvestedValue;
        this.totalCurrentValue = totalCurrentValue;
        this.stocks = stocks;
    }
}


