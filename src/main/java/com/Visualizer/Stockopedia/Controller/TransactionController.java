package com.Visualizer.Stockopedia.Controller;

import com.Visualizer.Stockopedia.Model.Transaction;
import com.Visualizer.Stockopedia.Service.RepositoryServices.Portfolio.PortfolioServiceImplementation;
import com.Visualizer.Stockopedia.Service.RepositoryServices.Transaction.TransactionServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transaction/")
final class TransactionController {

    TransactionServiceImplementation transactionService;

    final
    PortfolioServiceImplementation portfolioService;

    public TransactionController(TransactionServiceImplementation transactionService, PortfolioServiceImplementation portfolioService) {
        this.transactionService = transactionService;
        this.portfolioService = portfolioService;
    }

    @PutMapping("/add/{id}")
    public ResponseEntity<String> addTransaction(@PathVariable("id")String userId,
                                         @RequestBody Transaction transaction){
        transaction.setUserId(userId);
        transactionService.addToTransactionRepo(transaction);
        portfolioService.updatePortfolioWithTransaction(transaction);

        return ResponseEntity.ok("Transaction successfully added");
    }

    @PostMapping("/update/{userId}/{transactionId}")
    public ResponseEntity<String> updateTransaction(@PathVariable("userId")String userId,
                                                 @PathVariable("transactionId")String transactionId,
                                                 @RequestBody Transaction transaction){

        transaction.setUserId(userId);
        transaction.setTransactionId(transactionId);
        Transaction transaction1 = transactionService.updateTransaction(transaction);
        portfolioService.updatePortfolioWithTransaction(transaction1);

        return ResponseEntity.ok("Transaction successfully updated");
    }


    @DeleteMapping("/delete/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable("transactionId")String transactionId){

        //transaction.setUserId(userId);
        portfolioService.removeTransactionFromPortfolio(transactionService.getTransactionById(transactionId).get());
        transactionService.deleteById(transactionId);

        return ResponseEntity.ok("Transaction successfully deleted");
    }

    @GetMapping("/display/{userId}")
    public ResponseEntity<List<Transaction>> displayTransaction(@PathVariable("userId")String userId){

        Optional<List<Transaction>> optionalList = transactionService.getTransactionsByUserId(userId);

        if(optionalList.isPresent()){
            return ResponseEntity.ok(optionalList.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No transactions exist");
        }
    }


}
