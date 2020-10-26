package com.medical.medical.service;

import com.medical.medical.dtos.FullDetailPlanDto;
import com.medical.medical.dtos.PatientDtoCare;

import java.util.List;

public interface PatientService {
    PatientDtoCare getPatientById(Long id);

    List<FullDetailPlanDto> getPlansByPatientId(Long id);
}
