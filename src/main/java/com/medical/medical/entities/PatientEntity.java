package com.medical.medical.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "patient")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "medical_record")
    private String medicalRecord;

    @ManyToOne
    @JoinColumn(name = "doctor_fk")
    private DoctorEntity doctorEntity;

    @ManyToOne
    @JoinColumn(name = "caregiver_fk")
    private CaregiverEntity caregiverEntity;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "plan",
            joinColumns = { @JoinColumn(name = "patient_fk") },
            inverseJoinColumns = { @JoinColumn(name = "medication_fk") }
    )
    List<MedicationEntity> medications = new ArrayList<>();
}
