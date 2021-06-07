package com.Visualizer.Stockopedia.Repository;

import com.Visualizer.Stockopedia.Model.Portfolio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PortfolioRepository extends MongoRepository<Portfolio,String> {

    //find a portfolio - findbyid

    //add a new portfolio - insert

    //delete portfolio - deletebyid

    //update portfolio - save
}
