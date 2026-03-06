package org.gagan.routematic_clone.service;

import org.gagan.routematic_clone.model.Vehicle;
import org.gagan.routematic_clone.repository.VehicleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public ResponseEntity<Vehicle> createVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        return ResponseEntity.ok(vehicle);
    }

    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return ResponseEntity.ok(vehicles);
    }

    public ResponseEntity<Vehicle> getVehicleById(UUID id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Vehicle> updateVehicle(UUID id, Vehicle updatedVehicle) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        if (vehicleOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Vehicle vehicle = vehicleOptional.get();
        vehicle.setVehicleNumber(updatedVehicle.getVehicleNumber());
        vehicle.setType(updatedVehicle.getType());
        vehicle.setCapacity(updatedVehicle.getCapacity());
        vehicle.setStatus(updatedVehicle.getStatus());
        vehicleRepository.save(vehicle);
        return ResponseEntity.ok(vehicle);
    }

    public ResponseEntity<Void> deleteVehicle(UUID id) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        if (vehicleOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        vehicleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
