package com.bscott.chore.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ChoreTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChoreTrackerApplication.class, args);
    }
}
