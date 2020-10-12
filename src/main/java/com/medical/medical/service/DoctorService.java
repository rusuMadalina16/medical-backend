package com.medical.medical.service;

import com.medical.medical.dtos.*;
import com.medical.medical.entities.MedicationEntity;

import java.util.List;

public interface DoctorService {
    void addPatient(PatientEntityDto patientEntityDto);

    DoctorDto getDoctorById(Long doctorId);

    void deletePatientById(Long id);

    List<PatientEntityDto> getPatients(Long doctorId);

    void addCaregiver(CaregiverDto caregiverDto);

    List<CaregiverDto> getCaregivers();

    void deleteCaregiverById(Long id);

    void addMedication(MedicationDto medicationDto);

    List<MedicationDto> getMedications();

    void deleteMedicationById(Long id);

    void addPlan(PlanDto planDto);
}
