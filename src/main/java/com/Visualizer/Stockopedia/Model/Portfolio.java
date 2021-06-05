package com.Visualizer.Stockopedia.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
@Data
public class Portfolio {

    @Id
    private String portfolioId;

    private String userId;

    private Double totalInvestedValue;

    private Double totalCurrentValue;

    private ArrayList<Stocks> stocks;
}


