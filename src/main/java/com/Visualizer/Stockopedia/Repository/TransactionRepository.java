package com.Visualizer.Stockopedia.Repository;

import com.Visualizer.Stockopedia.Model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction,String> {
}
