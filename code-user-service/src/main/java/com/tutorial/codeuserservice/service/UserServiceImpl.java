package com.tutorial.codeuserservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tutorial.codeuserservice.entity.User;
import com.tutorial.codeuserservice.feignclient.BikeFeignClient;
import com.tutorial.codeuserservice.feignclient.CarFeignClient;
import com.tutorial.codeuserservice.model.Bike;
import com.tutorial.codeuserservice.model.Car;
import com.tutorial.codeuserservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CarFeignClient carFeignClient;

	@Autowired
	private BikeFeignClient bikeFeignClient;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public List<Car> carFindByUserId(Long userId) {
		List<Car> cars = restTemplate.getForObject("http://localhost:8002/cars/user/" + userId, List.class);
		return cars;
	}
	
	@Override
	public List<Bike> bikeFindByUserId(Long userId) {
		List<Bike> cars = restTemplate.getForObject("http://localhost:8003/bikes/user/" + userId, List.class);
		return cars;
	}

	@Override
	public Car save(Long userId, Car car) {
		car.setUserId(userId);
		return carFeignClient.save(car);
	}

	@Override
	public Bike save(Long userId, Bike bike) {
		bike.setUserId(userId);
		return bikeFeignClient.save(bike);
	}

	@Override
	public Map<String, Object> getAllUserVehicles(Long userId) {
		
		Map<String, Object> map = new HashMap<>();
		
		User user = userRepository.findById(userId).orElse(null);
		
		if (user == null) {
			map.put("mensaje", "El usuario no existe");
		}
		
		map.put("user", user);
		
		List<Car> cars = carFeignClient.findByUserId(userId);
		
		map.put("cars:", cars);
		
		List<Bike> bikes = bikeFeignClient.findByUserId(userId);
		
		map.put("bikes", bikes);
		
		return map;
	}
	
	

}
