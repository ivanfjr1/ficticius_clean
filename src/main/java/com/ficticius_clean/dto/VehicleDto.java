package com.ficticius_clean.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
public class VehicleDto {

	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private String brand;
	
	@Getter @Setter
	private String model;
	
	@Getter @Setter
	private Date fabricationDate;
	
	@Getter @Setter
	private Double consumptionMiddleCityKml;
	
	@Getter @Setter
	private Double consumptionMiddleHighwayKml;

}
