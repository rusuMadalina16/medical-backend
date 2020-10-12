package com.medical.medical.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "medication")
public class MedicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "side_effects")
    private String sideEffects;

    @Column(name = "dosage")
    private String dosage;

    @ManyToMany(mappedBy = "medications")
    private List<PatientEntity> patients = new ArrayList<>();
}
