package com.Visualizer.Stockopedia.Repository;

import com.Visualizer.Stockopedia.Model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TransactionRepository extends MongoRepository<Transaction,String> {
    //find a transaction - findbyid

    //add a new transaction - insert

    //delete - deletebyid

    //update - save
}
