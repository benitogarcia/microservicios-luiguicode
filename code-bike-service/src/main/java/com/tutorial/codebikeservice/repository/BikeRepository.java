package com.tutorial.codebikeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.codebikeservice.entity.Bike;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {
	
	List<Bike> findByUserId(Long userId);

}
