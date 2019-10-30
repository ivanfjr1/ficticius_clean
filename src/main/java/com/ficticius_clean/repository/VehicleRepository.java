package com.ficticius_clean.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ficticius_clean.model.Vehicle;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, UUID> {

}
