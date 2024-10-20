package com.example.springbootspark;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Component;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

@Component
public class SparkJob {
    public void runSparkJob() {
        System.out.println("Running Spark Word Count Job");
        SparkSession spark = SparkSession.builder()
                .appName("Word Count Spark")
                .master("spark://790f2ec16bc3:7077")
                .getOrCreate();

        try (JavaSparkContext sc = new JavaSparkContext(spark.sparkContext())) {
            sc.setJobGroup("WordCountJob", "Counting words from dataset");
            List<String> data = Arrays.asList(
                    "Hello Spark World",
                    "Spark is great",
                    "Hello Spark",
                    "Java and Spark"
            );
            JavaRDD<String> rdd = sc.parallelize(data);

            // Split each line into words
            JavaRDD<String> words = rdd.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

            // Map each word to a tuple (word, 1)
            JavaPairRDD<String, Integer> wordPairs = words.mapToPair(word -> new Tuple2<>(word, 1));

            // Reduce by key (word) to get word counts
            JavaPairRDD<String, Integer> wordCounts = wordPairs.reduceByKey(Integer::sum);

            // Collect and print the results
            List<Tuple2<String, Integer>> result = wordCounts.collect();
            System.out.println("\u001B[34mWord Count Results\u001B[0m");
            result.forEach(tuple -> System.out.println(tuple._1() + ": " + tuple._2()));
        } finally {
            spark.stop();
        }
    }
}
