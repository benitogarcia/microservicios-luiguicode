package com.tutorial.codeuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CodeUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeUserServiceApplication.class, args);
	}

}
