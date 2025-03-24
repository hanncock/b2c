package com.example.b2c;
import com.example.b2c.dto.AcknowledgeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class B2c {
    public static void main(String[] args){

        SpringApplication.run(B2c.class, args);

    }

//    @Bean
//    public OkHttpClient getOkHttpClient() {
//        return new OkHttpClient();
//    }
//
//    @Bean
//    public ObjectMapper getObjectMapper() {
//        return new ObjectMapper();
//    }
//
//    @Bean
//    public AcknowledgeResponse getAcknowledgeResponse() {
//        AcknowledgeResponse acknowledgeResponse = new AcknowledgeResponse();
//        acknowledgeResponse.setMessage("Success");
//        return acknowledgeResponse;
//    }
}
