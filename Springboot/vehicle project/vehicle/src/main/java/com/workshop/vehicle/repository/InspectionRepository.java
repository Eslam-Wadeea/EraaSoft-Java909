package com.workshop.vehicle.repository;

import com.workshop.vehicle.model.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Long> {
    List<Inspection> findByVehicleIdOrderByInspectionDateDesc(Long vehicleId);
}