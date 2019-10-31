package com.ficticius_clean.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class SpendForecastOutputDto {

	@Getter @Setter
	String name;
	
	@Getter @Setter
	String brand;
	
	@Getter @Setter
	String model;
	
	@Getter @Setter
	Integer fabricationDate;
	
	@Getter @Setter
	Double totalFuelSpent;
	
	@Getter @Setter
	Double totalSpending;

}
