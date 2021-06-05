package com.Visualizer.Stockopedia.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class Transaction {

    @Id
    private String transactionId;

    private String userId;

    private LocalDateTime dateTime;

    private String symbol;

    private Long quantity;

    private Double price;

    private Type type;

}

enum Type{
    Buy,
    Sell
}
