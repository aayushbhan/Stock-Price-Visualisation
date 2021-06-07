package com.Visualizer.Stockopedia.Service.RepositoryServices.Transaction;

import com.Visualizer.Stockopedia.Model.Transaction;

import java.util.Optional;
import java.util.List;

public interface TransactionService {

    String createTransaction(Transaction transaction);
    Optional<List<Transaction>> getTransactionsByUserId(String userId);
    Optional<Transaction> getTransactionById(String transactionId);
    void updateDate(String userId,String transactionId);
    void updateSymbol(String userId,String transactionId);
    void updateQuantity(String userId,String transactionId);
    void updateType(String userId,String transactionId);
    void updatePrice(String userId,String transactionId);
    void deleteById(String userId);

}
