package com.Visualizer.Stockopedia.Controller;

import com.Visualizer.Stockopedia.Model.Portfolio;
import com.Visualizer.Stockopedia.Model.Stocks;
import com.Visualizer.Stockopedia.Model.Symbol;
import com.Visualizer.Stockopedia.Model.TimeSeries;
import com.Visualizer.Stockopedia.Service.RepositoryServices.Portfolio.PortfolioServiceImplementation;
import com.Visualizer.Stockopedia.Service.RepositoryServices.User.UserServiceImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.io.FileWriter;

@RestController
@RequestMapping("/api/portfolio/")
@CrossOrigin( origins = "http://localhost:3000" , maxAge = 3600)
public class PortfolioController {

    private final Logger logger = LoggerFactory.getLogger(PortfolioController.class);

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

    @GetMapping("/existent/{userId}")
    public ResponseEntity<Boolean> checkPortfolioExists(@PathVariable("userId")String userId){

        List<Portfolio> portfolioList = portfolioService.getPortfolioByUserId(userId);

        if(!portfolioList.isEmpty()){
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/graph/{symbol}")
    public ResponseEntity<String> updateGraph(@PathVariable("symbol")String symbol) throws IOException, ParseException, InterruptedException {
        File resource = new ClassPathResource(symbol+".json").getFile();
        ObjectMapper objectMapper = new ObjectMapper();
        Symbol[] dataList = objectMapper.readValue(resource,Symbol[].class);

        File file = new File("/home/aayush/financial-dashboard/src/share-data.json");
        FileWriter fileWriter = new FileWriter(file, true);
        SequenceWriter sequenceWriter = objectMapper.writerWithDefaultPrettyPrinter().writeValuesAsArray(fileWriter);
        int pageNumber = 0;

        int i=0;
        for(Symbol s: dataList){
            TimeSeries temp = new TimeSeries();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = df.parse(s.getDate());
            long e_time = date.getTime();
            //System.out.println(epoch);

            temp.setDate("/Date("+ e_time +")/");
            temp.setOpen(s.getOpen());
            temp.setHigh(s.getHigh());
            temp.setLow(s.getLow());
            temp.setClose(s.getClose());
            temp.setVolume(s.getVolume());
            //Object to JSON in file

            sequenceWriter.writeAll(Collections.singleton(temp));
            pageNumber++;
            Thread.sleep(5000);
            logger.info(i +"********************AANKHEIN HUI NAM***************************");
            i++;

        }
        sequenceWriter.close();

        return ResponseEntity.ok("Data sent");
    }

}
