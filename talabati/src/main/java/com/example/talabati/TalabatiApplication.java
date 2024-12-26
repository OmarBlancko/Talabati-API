package com.example.talabati;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class TalabatiApplication {


    public static void main(String[] args) {

        SpringApplication.run(TalabatiApplication.class, args);
    }
    @Value("${spring.application.name}")
    private String name;

    @RequestMapping(value = "/")
    public String getName() {
        return name;
    }
}
