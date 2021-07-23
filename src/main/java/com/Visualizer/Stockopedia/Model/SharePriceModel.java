package com.Visualizer.Stockopedia.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("shareprice")
@Data
public class SharePriceModel {
    @Id
    private String symbol;

    List<OhlcModel> ohlcModelList;


    public SharePriceModel(String symbol, List<OhlcModel> ohlcModelList) {
        this.symbol = symbol;
        this.ohlcModelList = ohlcModelList;
    }
}


/*
{
    "symbol": "AAPL",
    "price" : [ {
                },{}

        ]
}

https://landy.website/

https://github.com/Adrinlol/landy-react-template
* */