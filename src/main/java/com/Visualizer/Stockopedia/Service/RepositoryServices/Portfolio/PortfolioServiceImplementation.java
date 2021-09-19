package com.Visualizer.Stockopedia.Service.RepositoryServices.Portfolio;

import com.Visualizer.Stockopedia.Model.*;
import com.Visualizer.Stockopedia.Repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PortfolioServiceImplementation implements PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Override
    public String createPortfolio(User user) {

        //Creates a portfolio with default values and add to the repo
        Portfolio p1 = new Portfolio(user.getUserId(),0.00,0.00, new ArrayList<>());
        Portfolio port = portfolioRepository.insert(p1);

        return port.getPortfolioId();
    }

    @Override
    public void updatePortfolioWithTransaction(Transaction transaction) {

        //get userId of given parameter
        String currUserID = transaction.getUserId();

        //get portfolio of given user
        Portfolio currPort = portfolioRepository.findAll()
                                                .stream()
                                                .filter(p -> p.getUserId().equals(currUserID))
                                                .collect(Collectors.toList())
                                                .get(0);

        //get the list of stocks of the user
        ArrayList<Stocks> stocksList1 = currPort.getStocks();

        //checks if the user already has the stock in his portfolio
        boolean hasSymbol = stocksList1.stream()
                                       .anyMatch( s -> s.getSymbol().equals(transaction.getSymbol()));
        int idx = 0;


        if(hasSymbol){

            //gets index of stock which the user already has for updating
            for (int i = 0; i < stocksList1.size(); i++) {
                if(stocksList1.get(i).getSymbol().equals(transaction.getSymbol())){
                    idx = i;
                    break;
                }
            }

            Stocks currStock = stocksList1.get(idx);
            currStock.setShares(currStock.getShares()+transaction.getQuantity());
            currStock.setAvgPrice((currStock.getAvgPrice()+transaction.getPrice())/2);
            currStock.setInvestedValue(currStock.getInvestedValue()+(transaction.getQuantity()*transaction.getPrice()));
            currStock.calculateCurrentValue();
            currStock.calculateProfit();
            stocksList1.remove(idx);
            stocksList1.add(currStock);
        }else {

            Stocks newStock = new Stocks();
            newStock.setSymbol(transaction.getSymbol());
            newStock.setShares(transaction.getQuantity());
            newStock.setAvgPrice(transaction.getPrice());
            newStock.setInvestedValue(transaction.getQuantity()*transaction.getPrice());
            newStock.calculateCurrentValue();
            newStock.calculateProfit();
            stocksList1.add(newStock);
        }

        currPort.setStocks(stocksList1);
        portfolioRepository.save(currPort);
        portfolioRepository.save(updateTotalInvestedValue(transaction));
        //portfolioRepository.save(updateTotalCurrentValue(transaction));
    }

    @Override
    public List<Stocks> getStocksById(String userId) {

        //gets the stock list of the user with given userId
        Portfolio portfolio1 = portfolioRepository.findAll()
                                                    .stream()
                                                    .filter( p -> p.getUserId().equalsIgnoreCase(userId))
                                                    .collect(Collectors.toList())
                                                    .get(0);

        return portfolio1.getStocks();
    }

    @Override
    public void removeTransactionFromPortfolio(Transaction transaction) {
        //get userId of given parameter
        String currUserID = transaction.getUserId();

        //get portfolio of given user
        Portfolio currPort = portfolioRepository.findAll()
                                                .stream()
                                                .filter(p -> p.getUserId().equalsIgnoreCase(currUserID))
                                                .collect(Collectors.toList())
                                                .get(0);

        //get the list of stocks of the user
        ArrayList<Stocks> stocksList1 = currPort.getStocks();

        int idx = 0;

        //gets details of stock which the user already has for updating
        for (int i = 0; i < stocksList1.size(); i++) {
            if(stocksList1.get(i).getSymbol().equals(transaction.getSymbol())){
                idx = i;
                break;
            }
        }

        stocksList1.remove(idx);
        currPort.setStocks(stocksList1);
        portfolioRepository.save(currPort);
        transaction.setType("Sell");
        portfolioRepository.save(updateTotalInvestedValue(transaction));
        //portfolioRepository.save(updateTotalCurrentValue(transaction));
    }

    @Override
    public Portfolio updateTotalInvestedValue(Transaction transaction) {
        boolean isBuy = transaction.getType().equalsIgnoreCase("Buy");
        boolean isSell = transaction.getType().equalsIgnoreCase("Sell");

        //get userId of given parameter
        String currUserID = transaction.getUserId();

        //get portfolio of given user
        Portfolio currPort = portfolioRepository.findAll().stream().filter(p -> p.getUserId().equalsIgnoreCase(currUserID)).collect(Collectors.toList()).get(0);

        //update the invested value depending on what the transaction is
        if(isBuy)
            currPort.setTotalInvestedValue(currPort.getTotalInvestedValue() + (transaction.getQuantity() * transaction.getPrice()));
        else if(isSell)
            currPort.setTotalInvestedValue(currPort.getTotalInvestedValue() - (transaction.getQuantity() * transaction.getPrice()));

        return currPort;
    }

    @Override
    public Portfolio updateTotalCurrentValue(Transaction transaction) {

        return null;
    }


    @Override
    public void deleteById(String portfolioId) {
        portfolioRepository.deleteById(portfolioId);
    }

    @Override
    public List<Portfolio> getPortfolioByUserId(String userId) {

            //gets list of all portfolios of the user with given userId

        return (portfolioRepository.findAll()
                                   .stream()
                                   .filter( t -> t.getUserId().equalsIgnoreCase(userId))
                                   .collect(Collectors.toList()));

    }

    @Override
    public void deleteAllPortfoliosOfUser(String userId) {

        getPortfolioByUserId(userId).stream()
                                    .map(Portfolio::getPortfolioId)
                                    .forEach(this::deleteById);
    }
}
