package com.tutorial.codeuserservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

	private long id;

	private String brand;

	private String model;

	private Long userId;

}
