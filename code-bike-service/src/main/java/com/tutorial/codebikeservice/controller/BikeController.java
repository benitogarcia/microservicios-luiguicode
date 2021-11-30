package com.tutorial.codebikeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.codebikeservice.entity.Bike;
import com.tutorial.codebikeservice.service.BikeService;


@RestController
@RequestMapping("/bikes")
public class BikeController {
	
	@Autowired
	private BikeService bikeService;

	@GetMapping
	public ResponseEntity<List<Bike>> findAll() {
		List<Bike> Bikes = bikeService.findAll();
		if (Bikes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(Bikes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Bike> findById(@PathVariable("id") Long id) {
		Bike Bike = bikeService.findById(id);
		if (Bike == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(Bike);
	}

	@PostMapping("/save")
	public ResponseEntity<Bike> save(@RequestBody Bike Bike) {
		Bike Bikesave = bikeService.save(Bike);
		if (Bikesave == null) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(Bike);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Bike>> findByUserId(@PathVariable("userId") Long userId) {
		List<Bike> Bikes = bikeService.findByUserId(userId);
		if (Bikes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(Bikes);
	}
}
