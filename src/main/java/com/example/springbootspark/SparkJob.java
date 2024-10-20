package com.example.springbootspark;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SparkJob {
    public void runSparkJob() {
        System.out.println("Running Spark Job");
        SparkSession spark = SparkSession.builder()
                .appName("Hello World Spark")
                .master("local[*]")
                .getOrCreate();

        try (JavaSparkContext sc = new JavaSparkContext(spark.sparkContext())) {
            System.out.println("Hello World Context");
            List<String> data = Arrays.asList("Hello", "World", "from", "Test", "Spark");
            JavaRDD<String> rdd = sc.parallelize(data);
            System.out.println("\u001B[31m" + "Hello World RDD" + "\u001B[0m");
            rdd.foreach(w -> System.out.println(w));
        } finally {
            spark.stop();
        }

    }
}
