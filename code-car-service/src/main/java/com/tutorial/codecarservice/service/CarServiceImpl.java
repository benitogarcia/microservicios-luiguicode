package com.tutorial.codecarservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorial.codecarservice.entity.Car;
import com.tutorial.codecarservice.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarRepository carrRepository;

	@Override
	public List<Car> findAll() {
		return carrRepository.findAll();
	}

	@Override
	public Car save(Car user) {
		return carrRepository.save(user);
	}

	@Override
	public Car findById(Long id) {
		return carrRepository.findById(id).orElse(null);
	}

	@Override
	public List<Car> findByUserId(Long userId) {
		return carrRepository.findByUserId(userId);
	}

}
