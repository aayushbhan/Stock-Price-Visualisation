package com.Visualizer.Stockopedia.Controller;

import com.Visualizer.Stockopedia.Service.financialService;
import com.Visualizer.Stockopedia.StockopediaApplication;
import com.Visualizer.Stockopedia.Repository.financialRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
///@RequestMapping(path = "api/v1/financial")
@AllArgsConstructor

public class financialController {
    /*private final financialService financialService;

    @GetMapping
    public List<StockopediaApplication> fetchAllfinancial()
    {
        return financialService.getfinancial();
    }*/
}
