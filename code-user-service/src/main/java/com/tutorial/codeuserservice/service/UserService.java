package com.tutorial.codeuserservice.service;

import java.util.List;
import java.util.Map;

import com.tutorial.codeuserservice.entity.User;
import com.tutorial.codeuserservice.model.Bike;
import com.tutorial.codeuserservice.model.Car;

public interface UserService {
	
	List<User> findAll();
	
	User save(User user);
	
	User findById(Long id);
	
	List<Car> carFindByUserId(Long userId);
	
	List<Bike> bikeFindByUserId(Long userId);
	
	Car save(Long userId, Car car);
	
	Bike save(Long userId, Bike bike);
	
	Map<String, Object> getAllUserVehicles(Long userId);

}
