package com.workshop.vehicle.repository;

import com.workshop.vehicle.model.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionRepository extends JpaRepository<Inspection,Long> {
}
