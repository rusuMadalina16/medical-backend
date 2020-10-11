package com.medical.medical.service;

import com.medical.medical.dtos.DoctorDto;
import com.medical.medical.dtos.PatientEntityDto;

public interface DoctorService {
    void addPatient(PatientEntityDto patientEntityDto);

    DoctorDto getDoctorById(Long doctorId);
}
