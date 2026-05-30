package com.workshop.vehicle.controller;

import com.workshop.vehicle.model.License;
import com.workshop.vehicle.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/licenses")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @PostMapping("/renew/{vehicleId}")
    public ResponseEntity<License> renewLicense(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(licenseService.renewLicense(vehicleId));
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<License> getLicenseInfo(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(licenseService.getLicenseByVehicleId(vehicleId));
    }
}
