package com.workshop;

import com.workshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.workshop.repository")
@EntityScan(basePackages = "com.workshop.model")
public class Task6Application implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(Task6Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        userService.addNewUserWithData();
        System.out.println("Data saved successfully!");
    }
}