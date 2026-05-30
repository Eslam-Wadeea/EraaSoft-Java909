package com.workshop.vehicle.service;

import com.workshop.vehicle.model.*;
import com.workshop.vehicle.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private InspectionRepository inspectionRepository;

    @Autowired
    private FineRepository fineRepository;

    public License renewLicense(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (!"APPROVED".equalsIgnoreCase(vehicle.getStatus())) {
            throw new RuntimeException("Cannot renew license for unapproved or pending vehicles.");
        }


        List<Inspection> inspections = inspectionRepository.findByVehicleIdOrderByInspectionDateDesc(vehicleId);
        if (inspections.isEmpty()) {
            throw new RuntimeException("Renewal Rejected: No inspection record found.");
        }

        Inspection latestInspection = inspections.get(0);
        if (!"PASSED".equalsIgnoreCase(latestInspection.getStatus())) {
            throw new RuntimeException("Renewal Rejected: The latest vehicle inspection did not pass.");
        }

        if (latestInspection.getInspectionDate().isBefore(LocalDate.now().minusMonths(6))) {
            throw new RuntimeException("Renewal Rejected: Latest inspection is older than 6 months.");
        }


        License license = licenseRepository.findByVehicleId(vehicleId)
                .orElse(new License());

        if (license.getId() == null) {
            license.setVehicle(vehicle);

            license.setExpiryDate(LocalDate.now());
        }


        if (LocalDate.now().isAfter(license.getExpiryDate())) {
            Fine fine = new Fine();
            fine.setAmount(150.00);
            fine.setReason("Late License Renewal Penalty");
            fine.setIssueDate(LocalDate.now());
            fine.setPaid(false);
            fine.setVehicle(vehicle);
            fine.setUser(vehicle.getOwner());
            fineRepository.save(fine);
        }

        license.setExpiryDate(LocalDate.now().plusYears(1));
        return licenseRepository.save(license);
    }

    public License getLicenseByVehicleId(Long vehicleId) {
        return licenseRepository.findByVehicleId(vehicleId)
                .orElseThrow(() -> new RuntimeException("License records not found for this vehicle"));
    }
}