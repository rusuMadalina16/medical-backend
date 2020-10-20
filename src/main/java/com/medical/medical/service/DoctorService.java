package com.medical.medical.service;

import com.medical.medical.dtos.*;

import java.util.List;

public interface DoctorService {
    void addPatient(PatientEntityDto patientEntityDto);

    DoctorDto getDoctorById(Long doctorId);

    void deletePatientById(Long id);

    List<PatientEntityDto> getPatients();

    void addCaregiver(CaregiverDto caregiverDto);

    List<CaregiverDto> getCaregivers();

    void deleteCaregiverById(Long id);

    void addMedication(MedicationDto medicationDto);

    List<MedicationDto> getMedications();

    void deleteMedicationById(Long id);

    void addPlan(PlanDto planDto);

    void updateMedication(MedicationDto medicationDto);

    void updatePatient(PatientEntityDto patientEntityDto);

    void updateCaregiver(CaregiverDto caregiverDto);

    PatientEntityDto getPatientById(Long id);

    CaregiverDto getCaregiverById(Long id);

    List<MedicationDto> getMedsByName(String name);

    List<PatientEntityDto> getPatientByName(String name);
}
