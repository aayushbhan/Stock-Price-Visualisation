package com.Visualizer.Stockopedia.Controller;

import com.Visualizer.Stockopedia.Model.Stocks;
import com.Visualizer.Stockopedia.Service.RepositoryServices.Portfolio.PortfolioServiceImplementation;
import com.Visualizer.Stockopedia.Service.RepositoryServices.User.UserServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio/")
public class PortfolioController {

    private final PortfolioServiceImplementation portfolioService;

    private final UserServiceImplementation userService;

    public PortfolioController(PortfolioServiceImplementation portfolioServiceImplementation, UserServiceImplementation userService) {
        this.portfolioService = portfolioServiceImplementation;
        this.userService = userService;
    }

    @PutMapping("/create/{userId}")
    public ResponseEntity<String> createPortfolio(@PathVariable("userId")String userId){
        portfolioService.createPortfolio(userService.findById(userId));

        return ResponseEntity.ok("Portfolio successfully added");
    }

    @GetMapping("/display/{userId}")
    public ResponseEntity<List<Stocks>> displayPortfolio(@PathVariable("userId")String userId){
        List<Stocks> stocksList = portfolioService.getStocksById(userId);

        if(!stocksList.isEmpty()){
            return ResponseEntity.ok(stocksList);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No stocks exist");
        }
    }

}
