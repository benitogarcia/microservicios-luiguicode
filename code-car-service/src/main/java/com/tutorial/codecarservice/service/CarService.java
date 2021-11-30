package com.tutorial.codecarservice.service;

import java.util.List;

import com.tutorial.codecarservice.entity.Car;

public interface CarService {
	
	List<Car> findAll();
	
	Car save(Car user);
	
	Car findById(Long id);
	
	List<Car> findByUserId(Long id);
	
	

}
