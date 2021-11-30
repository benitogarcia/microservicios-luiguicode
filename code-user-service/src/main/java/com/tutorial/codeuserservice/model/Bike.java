package com.tutorial.codeuserservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bike {

	private Long id;

	private String brand;

	private String model;

	private Long userId;

}
