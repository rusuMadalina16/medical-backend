package com.medical.medical.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "plan")
public class PlanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "patient_fk")
    private Long patientId;

    @Column(name = "medication_fk")
    private Long medicationId;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "stop_date")
    private String stopDate;

    @Column(name = "doctor_dosage")
    private String doctorDosage;

    @Column(name = "interval")
    private String interval;
}
