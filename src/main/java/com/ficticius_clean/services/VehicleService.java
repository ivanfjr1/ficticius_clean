package com.ficticius_clean.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ficticius_clean.dto.VehicleDto;
import com.ficticius_clean.model.Vehicle;
import com.ficticius_clean.repository.VehicleRepository;

@Service
public class VehicleService {
	
	@Autowired
	private VehicleRepository repo;
	
	public List<Vehicle> findAll() {
		return this.repo.findAll();
	}
	
	public Vehicle addVehicle(VehicleDto vehicleDto) {
		Vehicle vehicle = new Vehicle();
		
		vehicle.setId(UUID.randomUUID());
		vehicle.setName(vehicleDto.getName());
		vehicle.setBrand(vehicleDto.getBrand());
		vehicle.setModel(vehicleDto.getModel());
		vehicle.setFabricationDate(vehicleDto.getFabricationDate());
		vehicle.setConsumptionMiddleCityKml(vehicleDto.getConsumptionMiddleCityKml());
		vehicle.setConsumptionMiddleHighwayKml(vehicleDto.getConsumptionMiddleHighwayKml());
		
		return repo.save(vehicle);
	}
	
	public Vehicle getVehicle(UUID id) throws Exception {
		return repo.findById(id)
				.orElseThrow(() -> new Exception("Vehicle not found - " + id));
	}
	
	public Vehicle updateVehicle(Vehicle vehicle) throws Exception {
		Vehicle vehicleToUpdate = new Vehicle();
		
		vehicleToUpdate = this.getVehicle(vehicle.getId());
		vehicleToUpdate.setName(vehicle.getName());
		vehicleToUpdate.setModel(vehicle.getModel());		
		vehicleToUpdate.setBrand(vehicle.getBrand());		
		vehicleToUpdate.setFabricationDate(vehicle.getFabricationDate());
		vehicleToUpdate.setConsumptionMiddleCityKml(vehicle.getConsumptionMiddleCityKml());		
		vehicleToUpdate.setConsumptionMiddleHighwayKml(vehicle.getConsumptionMiddleHighwayKml());

		return this.repo.save(vehicleToUpdate);
	}
	
	public void deleteVehicle(UUID id) {
		this.repo.deleteById(id);
	}
	
	public void deleteAllVehicle() {
		this.repo.deleteAll();
	}
	
}
