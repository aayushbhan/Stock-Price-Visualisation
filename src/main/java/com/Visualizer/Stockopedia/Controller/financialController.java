package com.Visualizer.Stockopedia.Controller;

import com.Visualizer.Stockopedia.Service.RestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/financial")
//@AllArgsConstructor

public class financialController {
    private final RestService restService;

    public financialController(RestService restService) {
        this.restService = restService;
    }
    /*@GetMapping
    public String fetchAllfinancial()
    {
        return restService.getPostsPlainJSON();
    }*/
    @GetMapping("/AAPL")
    public String getStaticdata() {
        try {
            restService.getPostWithResponseHandling();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
