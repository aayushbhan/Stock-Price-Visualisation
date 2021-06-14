package com.Visualizer.Stockopedia.Service.RepositoryServices.Transaction;

import com.Visualizer.Stockopedia.Model.Transaction;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

public interface TransactionService {

    String addToTransactionRepo(Transaction transaction);
    Optional<List<Transaction>> getTransactionsByUserId(String userId);
    Optional<Transaction> getTransactionById(String transactionId);
    void updateDate(LocalDateTime localDate, String transactionId);
    void updateSymbol(String userId,String transactionId);
    void updateQuantity(Long quantity,String transactionId);
    void updateType(String type,String transactionId);
    void updatePrice(Double price,String transactionId);
    void deleteById(String transactionId);

}
