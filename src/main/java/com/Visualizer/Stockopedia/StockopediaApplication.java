package com.Visualizer.Stockopedia;

import com.Visualizer.Stockopedia.Model.financialModel;
import com.Visualizer.Stockopedia.Service.RestService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class StockopediaApplication {
	/*private final RestService restService;

	public StockopediaApplication(RestService restService) {
		this.restService = restService;
	}*/

	public static void main(String[] args) {
		SpringApplication.run(StockopediaApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		// fetch posts as plain JSON
		System.out.println(restService.getPostsPlainJSON());

	*//*	// fetch posts as object
		for (financialModel fm : restService.getPostsAsObject()) {
			System.out.println(fm);
		}*//*

		// fetch post with url parameter
//		System.out.println(restService.getPostWithUrlParameters());

		// fetch post with response handling
		//System.out.println(restService.getPostWithResponseHandling());

		// fetch post with response handling
		//System.out.println(restService.getPostWithCustomHeaders());
	}*/
}
