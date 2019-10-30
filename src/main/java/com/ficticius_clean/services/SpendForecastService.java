package com.ficticius_clean.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ficticius_clean.dto.SpendForecastInputDto;
import com.ficticius_clean.dto.SpendForecastOutputDto;
import com.ficticius_clean.model.Vehicle;

@Service
public class SpendForecastService {
	
	@Autowired
	VehicleService vehicleService;
	
	public List<SpendForecastOutputDto> calculateSpendingForecast(SpendForecastInputDto input) {
		List<SpendForecastOutputDto> sfVehicles = new ArrayList<>();

		List<Vehicle> vehicles = vehicleService.findAll();
		
		for (Vehicle vehicle : vehicles) {
			SpendForecastOutputDto sfVehicle = new SpendForecastOutputDto();
			
			sfVehicle.setBrand(vehicle.getBrand());
			sfVehicle.setFabricationDate(vehicle.getFabricationDate());
			sfVehicle.setModel(vehicle.getModel());
			sfVehicle.setName(vehicle.getName());
			sfVehicle.setTotalFuelSpent(this.calculateTotalFuelSpent(vehicle, input));
			sfVehicle.setTotalSpending(this.calculateTotalSpending(vehicle, input));
			
			sfVehicles.add(sfVehicle);
		}
		
		sfVehicles = sfVehicles.stream()
				.sorted(Comparator.comparing(SpendForecastOutputDto::getTotalSpending))
				.collect(Collectors.toList());
		
		return orderBySpending(sfVehicles);
	}
	
	private List<SpendForecastOutputDto> orderBySpending(List<SpendForecastOutputDto> sfVehicles) {
		return sfVehicles;
	}
	
	public Double calculateTotalFuelSpent(Vehicle vehicle, SpendForecastInputDto sfInput) {
		Double totalSpentCity = 0.0;
		Double totalSpentHighway = 0.0;
		
		if (sfInput.getTotalKmCity() != null)
			totalSpentCity = (sfInput.getTotalKmCity() / vehicle.getConsumptionMiddleCityKml());
		
		if (sfInput.getTotalKmHighway() != null)
			totalSpentHighway = (sfInput.getTotalKmHighway() / vehicle.getConsumptionMiddleHighwayKml());
		
		return totalSpentCity + totalSpentHighway;
	}
	
	public Double calculateTotalSpending(Vehicle vehicle, SpendForecastInputDto sfInput) {
		return calculateTotalFuelSpent(vehicle, sfInput) * sfInput.getGasolinePrice();
	}

}
