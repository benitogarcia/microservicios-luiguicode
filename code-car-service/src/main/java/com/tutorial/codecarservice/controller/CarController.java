package com.tutorial.codecarservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.codecarservice.entity.Car;
import com.tutorial.codecarservice.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {
	
	@Autowired
	private CarService CarService;

	@GetMapping
	public ResponseEntity<List<Car>> findAll() {
		List<com.tutorial.codecarservice.entity.Car> Cars = CarService.findAll();
		if (Cars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(Cars);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Car> findById(@PathVariable("id") Long id) {
		Car Car = CarService.findById(id);
		if (Car == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(Car);
	}

	@PostMapping("/save")
	public ResponseEntity<Car> save(@RequestBody Car Car) {
		Car Carsave = CarService.save(Car);
		if (Carsave == null) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(Car);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Car>> findByUserId(@PathVariable("userId") Long userId) {
		List<Car> cars = CarService.findByUserId(userId);
		if (cars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cars);
	}
}
