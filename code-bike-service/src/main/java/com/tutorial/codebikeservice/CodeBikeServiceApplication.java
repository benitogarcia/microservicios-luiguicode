package com.tutorial.codebikeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CodeBikeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeBikeServiceApplication.class, args);
	}

}
