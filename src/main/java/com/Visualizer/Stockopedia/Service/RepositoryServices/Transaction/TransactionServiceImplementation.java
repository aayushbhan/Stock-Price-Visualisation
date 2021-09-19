package com.Visualizer.Stockopedia.Service.RepositoryServices.Transaction;

import com.Visualizer.Stockopedia.Model.Transaction;
import com.Visualizer.Stockopedia.Repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImplementation implements TransactionService{

    private final TransactionRepository transactionRepository;

    public TransactionServiceImplementation(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

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
    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteById(String transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    @Override
    public void deleteAllTransactionsOfUser(String userId) {

        if(getTransactionsByUserId(userId).isPresent())
                getTransactionsByUserId(userId).get()
                                               .stream()
                                               .map(Transaction::getTransactionId)
                                               .forEach(this::deleteById);

    }
}
