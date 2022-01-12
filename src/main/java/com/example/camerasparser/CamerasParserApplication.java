package com.example.camerasparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CamerasParserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamerasParserApplication.class, args);
    }

    @Bean
    RestTemplate getRestTemplate() {

        return new RestTemplate();
    }
}
