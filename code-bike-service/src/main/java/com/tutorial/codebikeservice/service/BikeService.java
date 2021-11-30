package com.tutorial.codebikeservice.service;

import java.util.List;

import com.tutorial.codebikeservice.entity.Bike;

public interface BikeService {
	
	Bike save(Bike bike);
	
	List<Bike> findAll();
	
	List<Bike> findByUserId(Long userId);
	
	Bike findById(Long id);

}
