package com.Visualizer.Stockopedia.Model;
import lombok.Data;

@Data
public class OhlcModel {
    private double open;

    private double high;

    private double low;

    private double close;

    private double volume;

    public OhlcModel(double open, double high, double low, double close, double volume) {
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }
}
