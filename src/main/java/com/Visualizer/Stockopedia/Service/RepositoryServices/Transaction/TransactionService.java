package com.Visualizer.Stockopedia.Service.RepositoryServices.Transaction;

import com.Visualizer.Stockopedia.Model.Transaction;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

public interface TransactionService {

    String addToTransactionRepo(Transaction transaction);
    Optional<List<Transaction>> getTransactionsByUserId(String userId);
    Optional<Transaction> getTransactionById(String transactionId);
    Transaction updateTransaction(Transaction transaction);
    void deleteById(String transactionId);

    void deleteAllTransactionsOfUser(String userId);
}
