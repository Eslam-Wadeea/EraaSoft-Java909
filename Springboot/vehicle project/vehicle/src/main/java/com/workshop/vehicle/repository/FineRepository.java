package com.workshop.vehicle.repository;

import com.workshop.vehicle.model.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FineRepository extends JpaRepository<Fine,Long> {
}
