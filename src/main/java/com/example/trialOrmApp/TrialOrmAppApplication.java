package com.example.trialOrmApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TrialOrmAppApplication {

	public static void main(String[] args) {

		ApplicationContext context =

		SpringApplication.run(TrialOrmAppApplication.class, args);

		Dev obj = context.getBean(Dev.class);

		obj.bild();
	}

}
