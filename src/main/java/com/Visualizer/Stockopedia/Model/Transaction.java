package com.Visualizer.Stockopedia.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Transaction")
@Data
public class Transaction {

    @Id
    private String transactionId;

    private String userId;

    private LocalDateTime dateTime;

    private String symbol;

    private Long quantity;

    private Double price;

    private String type;

    public Transaction(String userId, LocalDateTime dateTime, String symbol, Long quantity, Double price, String type) {
        this.userId = userId;
        this.dateTime = dateTime;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }
}
