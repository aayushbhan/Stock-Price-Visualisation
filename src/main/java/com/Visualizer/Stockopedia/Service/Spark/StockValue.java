package com.Visualizer.Stockopedia.Service.Spark;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.TaskContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaSparkContext$;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.*;
import org.springframework.beans.factory.annotation.Value;
import scala.Tuple2;

import java.util.*;

public class StockValue  {

    @Value(value = "${spring.kafka.consumer.group-id")
    private static String group2;
    @Value(value = "${spring.kafka.bootstrap-servers")
    private static String kafkabroker;
    public static void main(String[] args) throws InterruptedException
    {
        System.out.println("Started Streaming");

        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", kafkabroker);
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", group2);
        kafkaParams.put("auto.offset.reset", "earliest");

        Collection<String> topics = Collections.singletonList("stock1");

        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("StockValue");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaStreamingContext jssc = new JavaStreamingContext(sc, new Duration(100));

        //jssc.checkpoint("./.checkpoint");
        //DirectStream from Kafka topic
        JavaInputDStream<ConsumerRecord<String, String>> messages = KafkaUtils.createDirectStream(jssc, LocationStrategies.PreferConsistent(), ConsumerStrategies.<String, String> Subscribe(topics, kafkaParams));

        JavaPairDStream<String, String> results = messages.mapToPair(record -> new Tuple2<>(record.key(), record.value()));

        /*OffsetRange[] offsetRanges = {
                // topic, partition, inclusive starting offset, exclusive ending offset
                OffsetRange.create("test", 0, 0, 100),
                OffsetRange.create("test", 1, 0, 100)
        };

        JavaRDD<ConsumerRecord<String, String>> rdd = KafkaUtils.createRDD(
                sc,
                kafkaParams,
                offsetRanges,
                LocationStrategies.PreferConsistent()
        );*/



       messages.foreachRDD(rdd -> {
            OffsetRange[] offsetRanges = ((HasOffsetRanges) rdd.rdd()).offsetRanges();
            rdd.foreachPartition(consumerRecords -> {
                OffsetRange o = offsetRanges[TaskContext.get().partitionId()];
                System.out.println(
                        o.topic() + " " + o.partition() + " " + o.fromOffset() + " " + o.untilOffset());
            });
        });


        /*//results.print();
        JavaDStream<String> lines = results
                .map(
                        Tuple2::_2
                );
        JavaDStream<String> words = lines
                .flatMap(
                        x -> Arrays.asList(x.split("\\s+")).iterator()
                );
        JavaPairDStream<String, Integer> wordCounts = words
                .mapToPair(
                        s -> new Tuple2<>(s, 1)
                ).reduceByKey(
                        Integer::sum
                );

        wordCounts.print();*/
        //StreamingContext started
        jssc.start();
        jssc.awaitTermination();
    }
}
