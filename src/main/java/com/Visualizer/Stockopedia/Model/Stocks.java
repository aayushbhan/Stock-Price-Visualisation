package com.Visualizer.Stockopedia.Model;

import lombok.Data;

@Data
public class Stocks {

    private String symbol;

    private Long shares;

    private Double avgPrice;

    private Double investedValue;

    private Double currentValue;

    private Double profit;
}
