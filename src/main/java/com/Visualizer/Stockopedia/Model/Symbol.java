package com.Visualizer.Stockopedia.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Symbol {
    private String date;
    private double open;
    private double low;
    private double high;
    private double close;
    private double volume;
}
