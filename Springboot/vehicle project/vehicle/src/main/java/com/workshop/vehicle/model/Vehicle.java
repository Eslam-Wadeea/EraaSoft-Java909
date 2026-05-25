package com.workshop.vehicle.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // Phase 2: Plate number unique validation
    private String plateNumber;
    private String model;
    private String status; // PENDING, APPROVED, REJECTED (Phase 3)

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private License license;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Inspection> inspections;
}
