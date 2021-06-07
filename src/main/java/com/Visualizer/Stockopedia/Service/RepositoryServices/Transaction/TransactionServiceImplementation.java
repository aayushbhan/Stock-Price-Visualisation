package com.Visualizer.Stockopedia.Service.RepositoryServices.Transaction;

import com.Visualizer.Stockopedia.Model.Transaction;

import java.util.List;
import java.util.Optional;

public class TransactionServiceImplementation implements TransactionService{
    @Override
    public String createTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public Optional<List<Transaction>> getTransactionsByUserId(String userId) {
        return Optional.empty();
    }

    @Override
    public Optional<Transaction> getTransactionById(String transactionId) {
        return Optional.empty();
    }

    @Override
    public void updateDate(String userId, String transactionId) {

    }

    @Override
    public void updateSymbol(String userId, String transactionId) {

    }

    @Override
    public void updateQuantity(String userId, String transactionId) {

    }

    @Override
    public void updateType(String userId, String transactionId) {

    }

    @Override
    public void updatePrice(String userId, String transactionId) {

    }

    @Override
    public void deleteById(String userId) {

    }
}
