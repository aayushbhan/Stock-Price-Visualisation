package com.Visualizer.Stockopedia.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSeries{

    @JsonProperty("Date")
    private String Date;

    @JsonProperty("Close")
    private double Close;

    @JsonProperty("Volume")
    private double Volume;

    @JsonProperty("Open")
    private double Open;

    @JsonProperty("High")
    private double High;

    @JsonProperty("Low")
    private double Low;
}
