package com.Visualizer.Stockopedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.Visualizer.Stockopedia.Service.Kafka.*;

@SpringBootApplication
public class StockopediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockopediaApplication.class, args);
	}

}
