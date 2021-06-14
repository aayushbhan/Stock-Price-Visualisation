package com.Visualizer.Stockopedia.Model;

import lombok.Data;

@Data
public class Stocks {

    private String symbol;

    private long shares;

    private double avgPrice;

    private double investedValue;

    private double currentValue;

    private double profit;

    public void calculateCurrentValue(){

    }

    public void calculateProfit(){

    }

}
