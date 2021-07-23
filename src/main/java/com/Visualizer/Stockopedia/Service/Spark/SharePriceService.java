package com.Visualizer.Stockopedia.Service.Spark;

import com.Visualizer.Stockopedia.Model.AlphaVantageTimeSeriesDailyJson;
import com.Visualizer.Stockopedia.Service.AlphaVantage.JsonPOJODeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.*;
import org.apache.spark.streaming.kafka010.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.io.IOException;
import java.util.*;

@Service
public class SharePriceService {



    //private SparkToRepository sparkToRepository;

    private static final Logger logger = LoggerFactory.getLogger(SharePriceService.class);

    @Value(value="${spring.kafka.consumer.group-id")
    private String group1;

    public void SparkStream(String symbol) throws InterruptedException, IOException {
        logger.info("******************************Creating Kafka Parameter object***************************");
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "localhost:9092");
        kafkaParams.put("key.deserializer", JsonPOJODeserializer.class);
        kafkaParams.put("value.deserializer", JsonPOJODeserializer.class);
        kafkaParams.put("group.id", group1);
        kafkaParams.put("auto.offset.reset", "earliest");

        Collection<String> topics = Collections.singletonList("stock1");

        logger.info("******************************Streaming Context object created***************************");
        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("StockValue");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaStreamingContext jsc = new JavaStreamingContext(sc, new Duration(1000));

        //DirectStream from Kafka topic
        logger.info("******************************Dstream created***************************");
        JavaInputDStream<ConsumerRecord<AlphaVantageTimeSeriesDailyJson, String>> messages = KafkaUtils.createDirectStream(jsc, LocationStrategies.PreferConsistent(), ConsumerStrategies.Subscribe(topics, kafkaParams));

        logger.info("***********Starting Spark Stream Processing*********");
        JavaPairDStream<AlphaVantageTimeSeriesDailyJson, String> results = messages.mapToPair(record -> new Tuple2<>(record.key(), record.value()));
        logger.info(String.valueOf(results));
        JavaDStream<String> lines = results.map(Tuple2::_2);

        JavaDStream<String> words = lines.flatMap(x -> Arrays.asList(x.split("\\s+"))
                .iterator());
        logger.info(String.valueOf(words));
        JavaPairDStream<String, Integer> wordCounts = words.mapToPair(s -> new Tuple2<>(s, 1))
                .reduceByKey(Integer::sum);
        System.out.println(wordCounts);
        logger.info(String.valueOf(wordCounts));

        logger.info("****************Extracting Dstream****************");
        logger.info(String.valueOf(messages.count()));


        messages.foreachRDD( x-> {
            logger.info("****************Inside RDD************************");
            logger.info(x.collect().toString());
            String rddData = x.collect().toString();
            logger.info(rddData);
        });

        SparkToRepository.rddToDatabase(symbol);

        //StreamingContext started
        jsc.start();
        jsc.awaitTermination();
    }
}
