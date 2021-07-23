package com.Visualizer.Stockopedia.Repository;

import com.Visualizer.Stockopedia.Model.Portfolio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends MongoRepository<Portfolio,String> {

}
