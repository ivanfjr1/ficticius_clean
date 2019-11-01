package com.ficticius_clean.service;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ficticius_clean.FicticiusCleanApplicationTests;
import com.ficticius_clean.dto.SpendForecastInputDto;
import com.ficticius_clean.dto.SpendForecastOutputDto;
import com.ficticius_clean.model.Vehicle;
import com.ficticius_clean.services.SpendForecastService;
import com.google.gson.Gson;

public class VehicleServiceTest extends FicticiusCleanApplicationTests {

	Gson gson = new Gson();

	@Autowired
	private SpendForecastService spendForecastService;
	
	@Test
	public void economicInCity() {

		SpendForecastInputDto input = new SpendForecastInputDto();
		input.setGasolinePrice(3.89D);
		input.setTotalKmCity(10D);
		input.setTotalKmHighway(0D);

		List<SpendForecastOutputDto> ranking = this.spendForecastService.calculateSpendingForecast(input);

		SpendForecastOutputDto economic = ranking.get(0);

		Double consumptionFuelExpected = 0.92;
		Double valueFuelExpected = 3.58;

		assertEquals(economic.getTotalFuelSpent(), consumptionFuelExpected);
		assertEquals(economic.getTotalSpending(), valueFuelExpected);
	}

	@Test
	public void economicInHigway() {

		SpendForecastInputDto input = new SpendForecastInputDto();
		input.setGasolinePrice(3.89D);
		input.setTotalKmCity(0D);
		input.setTotalKmHighway(50D);

		List<SpendForecastOutputDto> ranking = this.spendForecastService.calculateSpendingForecast(input);

		SpendForecastOutputDto economic = ranking.get(0);

		Double consumptionFuelExpected = 4.03;
		Double valueFuelExpected = 15.68;

		assertEquals(economic.getTotalFuelSpent(), consumptionFuelExpected);
		assertEquals(economic.getTotalSpending(), valueFuelExpected);
	}

}
