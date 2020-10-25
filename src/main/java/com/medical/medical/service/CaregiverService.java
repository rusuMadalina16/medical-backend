package com.medical.medical.service;

import com.medical.medical.dtos.PatientEntityDto;

import java.util.List;

public interface CaregiverService {
    List<PatientEntityDto> getPatients(Long caregiverId);
}
