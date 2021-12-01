package com.tutorial.codeuserservice.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tutorial.codeuserservice.model.Bike;

@FeignClient(name = "bike-service")
@RequestMapping("/bikes")
public interface BikeFeignClient {

	@PostMapping("/save")
	Bike save(@RequestBody Bike Bike);
	
	@GetMapping("/user/{userId}")
	List<Bike> findByUserId(@PathVariable("userId") Long userId);
}
