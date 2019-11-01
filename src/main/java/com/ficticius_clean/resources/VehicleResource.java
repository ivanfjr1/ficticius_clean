package com.ficticius_clean.resources;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ficticius_clean.dto.VehicleDto;
import com.ficticius_clean.model.Vehicle;
import com.ficticius_clean.services.VehicleService;

@RestController
@RequestMapping(value="/api/vehicle")
public class VehicleResource {

	@Autowired
	private VehicleService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Vehicle>> findAll() {
		List<Vehicle> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	Vehicle getVehicle(@PathVariable UUID id) throws Exception {
		return service.getVehicle(id);
	}
	
	@PostMapping("/add")
	public boolean addVehicle(@Valid @RequestBody VehicleDto vehicleDto) {
		return this.service.addVehicle(vehicleDto) != null;
	}

	@PostMapping("/update")
	Vehicle updateVehicle(@RequestBody Vehicle vehicle) throws Exception {
		return this.service.updateVehicle(vehicle);
	}
	
	@DeleteMapping("/{id}") 
	void deleteVehicle(@PathVariable UUID id) {
		this.service.deleteVehicle(id);
	}
	
	@DeleteMapping("/deleteAllVehicle")
	void deleteAllVehicle() {
		this.service.deleteAllVehicle();
	}

	
}
