package com.example.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.example"})
@EnableJpaRepositories("com.example.springdemo.core.repository")
@EntityScan("com.example.springdemo.core.entity")
@EnableScheduling
public class SpringDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringDemoApplication.class, args);
    }

}
