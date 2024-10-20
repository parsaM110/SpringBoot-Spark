package com.example.springbootspark;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    private final SparkJob sparkJob;

    public HelloWorldController(SparkJob sparkJob) {
        this.sparkJob = sparkJob;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        try {
            sparkJob.runSparkJob();
            return "Hello World from Spark and Spring 🍃";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error running Spark job: " + e.getMessage();
        }
    }
}
