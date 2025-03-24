package com.example.b2c.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootApplication
public class DarajaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DarajaApiApplication.class, args);
    }

    @Bean
    public OkHttpClient getOkHttpClient() {
        return new OkHttpClient();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

}

//package com.example.b2c.utils;

//import java.nio.charset.StandardCharsets;
//import java.util.Base64;

