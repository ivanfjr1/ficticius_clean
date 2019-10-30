package com.ficticius_clean.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class SpendForecastInputDto {

	@Getter @Setter
	Double gasolinePrice;
	
	@Getter @Setter
	Double totalKmCity;
	
	@Getter @Setter
	Double totalKmHighway;

}
