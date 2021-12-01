package com.tutorial.codeeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CodeEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeEurekaServerApplication.class, args);
	}

}
