package com.ficticius_clean.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
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

		List<Vehicle> vehicles = vehicleService.findAll();

		return vehicles.stream()
				.map(vehicle -> this.calculateAverageConsumption(vehicle, input))
				.sorted(Comparator.comparingDouble(SpendForecastOutputDto::getTotalFuelSpent))
				.collect(Collectors.toList());        
	}


	private SpendForecastOutputDto calculateAverageConsumption(
			Vehicle vehicle,
			SpendForecastInputDto spendForecastInput
			) {        
		double consumptionCity = spendForecastInput.getTotalKmCity() / vehicle.getConsumptionMiddleCityKml();
		double consumptionHighway = spendForecastInput.getTotalKmHighway() / vehicle.getConsumptionMiddleHighwayKml();
		double consumptionTotal = BigDecimal.valueOf(consumptionCity + consumptionHighway)
				.setScale(2, RoundingMode.HALF_EVEN)
				.doubleValue();
		double totalSpending = BigDecimal.valueOf(consumptionTotal * spendForecastInput.getGasolinePrice())
				.setScale(2, RoundingMode.HALF_EVEN)
				.doubleValue();

		SpendForecastOutputDto spendForecastOutput = new SpendForecastOutputDto();
		spendForecastOutput.setName(vehicle.getName());
		spendForecastOutput.setBrand(vehicle.getBrand());
		spendForecastOutput.setModel(vehicle.getModel());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(vehicle.getFabricationDate());

		spendForecastOutput.setFabricationDate(calendar.getWeekYear());
		spendForecastOutput.setTotalFuelSpent(consumptionTotal);
		spendForecastOutput.setTotalSpending(totalSpending);

		return spendForecastOutput;

	}
}
