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
                .master("spark://localhost:7077")  // Connect to Spark master through localhost
                .config("spark.driver.host", "localhost")  // Important for connecting from outside Docker
                .config("spark.driver.bindAddress", "localhost")
                .config("spark.submit.deployMode", "client")
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
