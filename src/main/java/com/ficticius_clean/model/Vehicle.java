package com.ficticius_clean.model;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@NoArgsConstructor
public class Vehicle {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Getter @Setter
	private UUID id;
	
	@NotBlank
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