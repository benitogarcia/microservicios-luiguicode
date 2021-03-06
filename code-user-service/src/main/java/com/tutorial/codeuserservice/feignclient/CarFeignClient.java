package com.tutorial.codeuserservice.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tutorial.codeuserservice.model.Car;


@FeignClient(name = "car-service", url = "http://localhost:8002")
@RequestMapping("/cars")
public interface CarFeignClient {

	@PostMapping("/save")
	Car save(@RequestBody Car Car);
	
	@GetMapping("/user/{userId}")
	List<Car> findByUserId(@PathVariable("userId") Long userId);
	
}
