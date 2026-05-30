package com.workshop.vehicle.controller;

import com.workshop.vehicle.model.Vehicle;
import com.workshop.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleRepositoryService;

    @PostMapping("/register")
    public ResponseEntity<Vehicle> registerVehicle(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleRepositoryService.registerVehicle(vehicle));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleRepositoryService.getVehicleById(id));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(vehicleRepositoryService.getVehiclesByOwnerId(ownerId));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Vehicle> approveRegistration(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(vehicleRepositoryService.approveVehicleRegistration(id, status));
    }
}