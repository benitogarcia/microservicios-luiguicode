package com.tutorial.codegatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CodeGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeGatewayServiceApplication.class, args);
	}

}
