package com.Visualizer.Stockopedia.Service;

import com.Visualizer.Stockopedia.Model.RepositoryModel;
import com.Visualizer.Stockopedia.Model.financialModel;
import com.Visualizer.Stockopedia.Repository.financialRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.*;

@Service
public class RestService {
    private final RestTemplate restTemplate;
    private final financialRepository financialRepository;
    private final RepositoryModel repositoryModel;
    Logger logger = LoggerFactory.getLogger(RestService.class) ;

    // set connection and read timeouts
    public RestService(RestTemplateBuilder restTemplateBuilder, financialRepository financialRepository,RepositoryModel repositoryModel) {
        this.financialRepository = financialRepository;
        this.repositoryModel = repositoryModel;
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(500))
                .setReadTimeout(Duration.ofSeconds(500))
                .build();
    }
    //GET Request
    public String getPostsPlainJSON() throws IOException {
        String url = "https://fmpcloud.io/api/v3/financial-growth/AAPL?limit=20&apikey=565e4df67616e9ab2cd423373b900560";

        ObjectMapper objectMapper = new ObjectMapper();
        String givenData = Objects.requireNonNull(this.restTemplate.getForObject(url, String.class));
        financialModel financialModel1 = objectMapper.readValue(givenData,financialModel.class);
        return "success";

    }

    public financialModel[] getPostsAsObject() {
        String url = "https://fmpcloud.io/api/v3/income-statement/AAPL?limit=120&apikey=565e4df67616e9ab2cd423373b900560";
        return this.restTemplate.getForObject(url, financialModel[].class);
    }

    public financialModel getPostWithUrlParameters() {
        String url = "https://fmpcloud.io/api/v3/income-statement/AAPL?limit=120&apikey=565e4df67616e9ab2cd423373b900560";
        return this.restTemplate.getForObject(url, financialModel.class);
    }
    public String getPostWithResponseHandling() throws JSONException, JsonProcessingException {
        String url = "https://fmpcloud.io/api/v3/financial-growth/AAPL?limit=20&apikey=565e4df67616e9ab2cd423373b900560";
        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            ObjectMapper objectMapper = new ObjectMapper();
            financialModel[] fpojo = objectMapper.readValue(response.getBody(),financialModel[].class);
            List<financialModel> fmodel = Arrays.asList(fpojo);
            repositoryModel.setFinancialModelList(fmodel);
//            JSONArray array = new JSONArray(response.toString());
//            for (int i = 0; i < array.length(); i++) {
//                logger.info((String) array.get(0));
//
//                return response.getBody();
//                // return "";
            financialRepository.save(repositoryModel);
            }
        else {
            return "null";
        }
        return "";
    }
    public financialModel getPostWithCustomHeaders() {
        String url = "https://fmpcloud.io/api/v3/income-statement/AAPL?limit=120&apikey=565e4df67616e9ab2cd423373b900560";

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        // set custom header
        headers.set("x-request-src", "desktop");

        // build the request
        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        // use `exchange` method for HTTP call
        ResponseEntity<financialModel> response = this.restTemplate.exchange(url, HttpMethod.GET, entity, financialModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }
}

//55500958427