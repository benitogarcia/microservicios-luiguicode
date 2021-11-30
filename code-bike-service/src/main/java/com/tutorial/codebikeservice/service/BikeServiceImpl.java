package com.tutorial.codebikeservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.codebikeservice.entity.Bike;
import com.tutorial.codebikeservice.repository.BikeRepository;

@Service
public class BikeServiceImpl implements BikeService {
	
	@Autowired
	private BikeRepository bikeRepository;

	@Override
	public Bike save(Bike bike) {
		return bikeRepository.save(bike);
	}

	@Override
	public List<Bike> findAll() {
		return bikeRepository.findAll();
	}

	@Override
	public List<Bike> findByUserId(Long userId) {
		return bikeRepository.findByUserId(userId);
	}

	@Override
	public Bike findById(Long id) {
		return bikeRepository.findById(id).orElse(null);
	}

}
