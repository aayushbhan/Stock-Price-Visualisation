package com.Visualizer.Stockopedia.Service.Spark;

import com.mongodb.spark.config.WriteConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.TaskContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Duration;
import static java.util.Arrays.asList;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.*;
import com.mongodb.spark.MongoSpark;
import org.apache.spark.api.java.function.Function;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

public class StockValue  {

    private static final Logger logger = LoggerFactory.getLogger(StockValue.class);

    public void SparkStream() throws InterruptedException
    {
        logger.info("******************************Creating a SparkSession object***************************");
        SparkSession spark = SparkSession.builder()
                .master("local")
                .appName("MongoSparkConnectorIntro")
                .config("spark.mongodb.input.uri", "mongodb://127.0.0.1/test.myCollection")
                .config("spark.mongodb.output.uri", "mongodb://127.0.0.1/test.myCollection")
                .getOrCreate();

        logger.info("******************************Creating Kafka Parameter object***************************");
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "localhost:9092");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", group2);
        kafkaParams.put("auto.offset.reset", "earliest");

        Collection<String> topics = Collections.singletonList("stock1");

        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("StockValue");
        JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());
        JavaStreamingContext jsc = new JavaStreamingContext(conf, new Duration(1000));

        //jssc.checkpoint("./.checkpoint");
        //DirectStream from Kafka topic
        JavaInputDStream<ConsumerRecord<String, String>> messages = KafkaUtils.createDirectStream(jsc, LocationStrategies.PreferConsistent(), ConsumerStrategies.<String, String> Subscribe(topics, kafkaParams));

       /* // Create a custom WriteConfig
        Map<String, String> writeOverrides = new HashMap<String, String>();
        writeOverrides.put("collection", "spark");
        writeOverrides.put("writeConcern.w", "majority");
        WriteConfig writeConfig = WriteConfig.create(sc).withOptions(writeOverrides);*/

        // Create a RDD of 10 documents
        JavaRDD<Document> documents = sc.parallelize(asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).map
                (new Function<Integer, Document>() {
                    public Document call(final Integer i) throws Exception {
                        return Document.parse("{test: " + i + "}");
                    }
                });
       /******* Save data from RDD to MongoDB*****************/
        //MongoSpark.save(documents, writeConfig);
        MongoSpark.save(documents);
        System.out.println(messages);

       messages.foreachRDD(rdd -> {
            OffsetRange[] offsetRanges = ((HasOffsetRanges) rdd.rdd()).offsetRanges();
            rdd.foreachPartition(consumerRecords -> {
                OffsetRange o = offsetRanges[TaskContext.get().partitionId()];
                System.out.println(
                        o.topic() + " " + o.partition() + " " + o.fromOffset() + " " + o.untilOffset());
            });
        });

       //Sparkcontext closed
        sc.close();
        //StreamingContext started
        jsc.start();
        jsc.awaitTermination();
    }
}
