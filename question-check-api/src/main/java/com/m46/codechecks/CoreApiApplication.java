package com.m46.codechecks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.pivitengineering.store.model.entity"})
public class CoreApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreApiApplication.class, args);
    }
}
