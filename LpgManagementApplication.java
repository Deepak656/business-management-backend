package com.example.lpgmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.lpgmanagement.repositories")
public class LpgManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LpgManagementApplication.class, args);
    }
}