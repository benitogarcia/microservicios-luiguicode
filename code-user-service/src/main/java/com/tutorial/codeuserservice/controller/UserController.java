package com.tutorial.codeuserservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

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

	@CircuitBreaker(name = "carsCB", fallbackMethod = "fallBackFindAllCarByUserId")
	@GetMapping("/cars/{userId}")
	public ResponseEntity<List<Car>> findAllCarByUserId(@PathVariable("userId") Long userId) {
		User user = userService.findById(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		List<Car> cars = userService.carFindByUserId(userId);

		return ResponseEntity.ok(cars);
	}

	@CircuitBreaker(name = "carsCB", fallbackMethod = "fallBackSaveCar")
	@PostMapping(value = "/save/car/{userId}")
	public ResponseEntity<Car> saveCar(@PathVariable("userId") Long userId, @RequestBody Car car) {
		Car carsave = userService.save(userId, car);
		if (carsave == null) {

			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(carsave);
	}

	@CircuitBreaker(name = "bikesCB", fallbackMethod = "fallBackFindAllBikesByUserId")
	@GetMapping("/bikes/{userId}")
	public ResponseEntity<List<Bike>> findAllBikesByUserId(@PathVariable("userId") Long userId) {
		User user = userService.findById(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		List<Bike> bikes = userService.bikeFindByUserId(userId);

		return ResponseEntity.ok(bikes);
	}

	@CircuitBreaker(name = "bikesCB", fallbackMethod = "fallBackSaveBike")
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

	@CircuitBreaker(name = "allCB", fallbackMethod = "fallBackGetAllUserVehicles")
	@GetMapping("/vehicles/{userId}")
	public ResponseEntity<Map<String, Object>> getAllUserVehicles(@PathVariable("userId") Long id) {

		Map<String, Object> maps = new HashMap<String, Object>();
		maps = userService.getAllUserVehicles(id);
		return ResponseEntity.ok(maps);

	}

	private ResponseEntity<List<Car>> fallBackFindAllCarByUserId(@PathVariable("userId") Long userId,
			RuntimeException e) {
		List<Car> cars = new ArrayList<Car>();
		return new ResponseEntity(cars, HttpStatus.OK);
	}

	private ResponseEntity<Car> fallBackSaveCar(@PathVariable("userId") Long userId, @RequestBody Car car,
			RuntimeException e) {
		return new ResponseEntity(new Car(), HttpStatus.OK);
	}

	private ResponseEntity<List<Bike>> fallBackFindAllBikesByUserId(@PathVariable("userId") Long userId,
			RuntimeException e) {
		List<Bike> bikes = new ArrayList<Bike>();
		return new ResponseEntity(bikes, HttpStatus.OK);
	}

	private ResponseEntity<Bike> fallBackSaveBike(@PathVariable("userId") Long userId, @RequestBody Bike bike,
			RuntimeException e) {
		return new ResponseEntity(new Bike(), HttpStatus.OK);
	}

	private ResponseEntity<Map<String, Object>> fallBackGetAllUserVehicles(@PathVariable("userId") Long id,
			RuntimeException e) {
		Map<String, Object> map = new HashMap<>();

		map.put("user", new User());

		List<Car> cars = new ArrayList<Car>();

		map.put("cars:", cars);

		List<Bike> bikes = new ArrayList<Bike>();

		map.put("bikes", bikes);

		return new ResponseEntity(map, HttpStatus.OK);
	}

}
