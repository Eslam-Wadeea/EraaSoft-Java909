package com.workshop.vehicle.repository;

import com.workshop.vehicle.model.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {
    Optional<License> findByVehicleId(Long vehicleId);
}