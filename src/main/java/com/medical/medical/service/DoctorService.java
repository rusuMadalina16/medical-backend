package com.medical.medical.service;

import com.medical.medical.dtos.CaregiverDto;
import com.medical.medical.dtos.DoctorDto;
import com.medical.medical.dtos.PatientEntityDto;

import java.util.List;

public interface DoctorService {
    void addPatient(PatientEntityDto patientEntityDto);

    DoctorDto getDoctorById(Long doctorId);

    void deletePatientById(Long id);

    List<PatientEntityDto> getPatients(Long doctorId);

    void addCaregiver(CaregiverDto caregiverDto);

    List<CaregiverDto> getCaregivers();

    void deleteCaregiverById(Long id);
}
