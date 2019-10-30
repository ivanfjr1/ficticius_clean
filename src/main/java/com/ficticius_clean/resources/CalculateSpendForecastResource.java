package com.ficticius_clean.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ficticius_clean.dto.SpendForecastInputDto;
import com.ficticius_clean.dto.SpendForecastOutputDto;
import com.ficticius_clean.services.SpendForecastService;

@RestController
@RequestMapping("/api/calculate-spend-forecast")
public class CalculateSpendForecastResource {

	@Autowired
	SpendForecastService service;

	@PostMapping
	List<SpendForecastOutputDto> calculateSpendingForecast(@RequestBody SpendForecastInputDto input) {
		return service.calculateSpendingForecast(input);
	}
	
}
