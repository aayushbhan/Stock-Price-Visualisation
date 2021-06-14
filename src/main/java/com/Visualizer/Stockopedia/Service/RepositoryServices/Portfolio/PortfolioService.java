package com.Visualizer.Stockopedia.Service.RepositoryServices.Portfolio;

import com.Visualizer.Stockopedia.Model.Portfolio;
import com.Visualizer.Stockopedia.Model.Stocks;
import com.Visualizer.Stockopedia.Model.Transaction;
import com.Visualizer.Stockopedia.Model.User;

import java.util.List;

public interface PortfolioService {

    String createPortfolio(User user);
    void updatePortfolioWithTransaction(Transaction transaction);
    List<Stocks> getStocksById(String userId);
    void removeTransactionFromPortfolio(Transaction transaction);
    Portfolio updateTotalInvestedValue(Transaction transaction);
    Portfolio updateTotalCurrentValue(Transaction transaction);
}
