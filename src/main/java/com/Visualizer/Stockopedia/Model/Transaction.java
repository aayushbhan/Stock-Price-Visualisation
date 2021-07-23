package com.Visualizer.Stockopedia.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Transaction")
@Data
public class Transaction {

    @Id
    private String transactionId;

    private String userId;

    private String date;

    private String time;

    private String symbol;

    private Long quantity;

    private Double price;

    private String type;

    public Transaction(String userId, String date, String time, String symbol, Long quantity, Double price, String type) {
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }
}
