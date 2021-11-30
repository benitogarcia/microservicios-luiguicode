package com.tutorial.codeuserservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.codeuserservice.entity.User;
import com.tutorial.codeuserservice.model.Bike;
import com.tutorial.codeuserservice.model.Car;
import com.tutorial.codeuserservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> users = userService.findAll();
		if (users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable("id") Long id) {
		User user = userService.findById(id);
		if (user == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(user);
	}

	@PostMapping("/save")
	public ResponseEntity<User> save(@RequestBody User user) {
		User usersave = userService.save(user);
		if (usersave == null) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(user);
	}

	@GetMapping("/cars/{userId}")
	public ResponseEntity<List<Car>> findAllCarByUserId(@PathVariable("userId") Long userId) {
		User user = userService.findById(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		
		List<Car> cars = userService.carFindByUserId(userId);
		
		return ResponseEntity.ok(cars);
	}

	@GetMapping("/bikes/{userId}")
	public ResponseEntity<List<Bike>> findAllBikesByUserId(@PathVariable("userId") Long userId) {
		User user = userService.findById(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		
		List<Bike> bikes = userService.bikeFindByUserId(userId);
		
		return ResponseEntity.ok(bikes);
	}

	@PostMapping(value = "/save/car/{userId}")
	public ResponseEntity<Car> saveCar(@PathVariable("userId") Long userId, @RequestBody Car car) {
		Car carsave = userService.save(userId, car);
		if (carsave == null) {
			
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(carsave);
	}

	@PostMapping(value = "/save/bike/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bike> saveBike(@PathVariable("userId") Long userId, @RequestBody Bike bike) {
		Bike bikesave = userService.save(userId, bike);
		if (bikesave == null) {
			LOG.error("Error en el servidor.");
			return ResponseEntity.internalServerError().build();
		}
		LOG.info("Registro exitoso");
		return ResponseEntity.ok(bikesave);
	}
	
	@GetMapping("/vehicles/{userId}")
	public ResponseEntity<Map<String, Object>> getAllUserVehicles(@PathVariable("userId") Long id) {
		
		Map<String, Object> maps = new HashMap<String, Object>();
		maps = userService.getAllUserVehicles(id);
		return ResponseEntity.ok(maps);
		
	}

}
