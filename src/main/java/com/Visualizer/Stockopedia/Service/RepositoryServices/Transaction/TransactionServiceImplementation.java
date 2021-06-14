package com.Visualizer.Stockopedia.Service.RepositoryServices.Transaction;

import com.Visualizer.Stockopedia.Model.Transaction;
import com.Visualizer.Stockopedia.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionServiceImplementation implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public String addToTransactionRepo(Transaction transaction) {
        Transaction t1 = transactionRepository.insert(transaction);
        return t1.getTransactionId();
    }

    @Override
    public Optional<List<Transaction>> getTransactionsByUserId(String userId) {

        //gets list of all transactions of the user with given userId
        List<Transaction> listOfTransactions = transactionRepository.findAll()
                                                                    .stream()
                                                                    .filter( t -> t.getUserId().equalsIgnoreCase(userId))
                                                                    .collect(Collectors.toList());

        return Optional.of(listOfTransactions);
    }

    @Override
    public Optional<Transaction> getTransactionById(String transactionId) {
        return transactionRepository.findById(transactionId);
    }

    @Override
    public void updateDate(LocalDateTime newDate, String transactionId) {
        Optional<Transaction> temp = transactionRepository.findById(transactionId);

        Transaction transaction = null;

        if(temp.isPresent())
            transaction = temp.get();

        transaction.setDateTime(newDate);
        transactionRepository.save(transaction);
    }

    @Override
    public void updateSymbol(String symbol, String transactionId) {
        Optional<Transaction> temp = transactionRepository.findById(transactionId);

        Transaction transaction = null;

        if(temp.isPresent())
            transaction = temp.get();

        transaction.setSymbol(symbol);
        transactionRepository.save(transaction);
    }

    @Override
    public void updateQuantity(Long quantity, String transactionId) {
        Optional<Transaction> temp = transactionRepository.findById(transactionId);

        Transaction transaction = null;

        if(temp.isPresent())
            transaction = temp.get();

        transaction.setQuantity(quantity);
        transactionRepository.save(transaction);
    }

    @Override
    public void updateType(String type, String transactionId) {
        Optional<Transaction> temp = transactionRepository.findById(transactionId);

        Transaction transaction = null;

        if(temp.isPresent())
            transaction = temp.get();

        transaction.setType(type);
        transactionRepository.save(transaction);
    }

    @Override
    public void updatePrice(Double price, String transactionId) {
        Optional<Transaction> temp = transactionRepository.findById(transactionId);

        Transaction transaction = null;

        if(temp.isPresent())
            transaction = temp.get();

        transaction.setPrice(price);
        transactionRepository.save(transaction);
    }

    @Override
    public void deleteById(String transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
