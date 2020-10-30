package com.medical.medical.service;

import com.medical.medical.dtos.*;
import com.medical.medical.entities.PlanEntity;

import java.util.List;

public interface DoctorService {
    void addPatient(PatientEntityDto patientEntityDto);

    DoctorDto getDoctorById(Long doctorId);

    void deletePatientById(Long id);

    List<PatientDtoCare> getPatients();

    void addCaregiver(CaregiverDto caregiverDto);

    List<CaregiverDto> getCaregivers();

    void deleteCaregiverById(Long id);

    void addMedication(MedicationDto medicationDto);

    List<MedicationDto> getMedications();

    void deleteMedicationById(Long id);

    void addPlan(PlanDto planDto);

    void updateMedication(MedicationDto medicationDto);

    void updatePatient(PatientDtoCare patientDtoCare);

    void updateCaregiver(CaregiverDto caregiverDto);

    PatientEntityDto getPatientById(Long id);

    CaregiverDto getCaregiverById(Long id);

    List<MedicationDto> getMedsByName(String name);

    List<PatientEntityDto> getPatientByName(String name);

    List<CaregiverDto> getCaregiverByName(String name);

    void updatePatientCare(PatientDtoCare patientDtoCare);

    void updatePlanAgain(List<PlanEntity> meds);

    List<PatientDtoCare> getPatientsByDoctorId(Long doctorId);

    void addUser(PatientEntityDto patientEntityDto);

    void addUser2(CaregiverDto caregiverDto);

    void addDoctor(SignUpDto signUpDto);

    void addUser3(SignUpDto signUpDto);
}
