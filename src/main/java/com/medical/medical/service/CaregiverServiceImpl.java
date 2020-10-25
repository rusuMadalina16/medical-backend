package com.medical.medical.service;

import com.medical.medical.dtos.PatientEntityDto;
import com.medical.medical.entities.CaregiverEntity;
import com.medical.medical.entities.PatientEntity;
import com.medical.medical.helper.CaregiverMapper;
import com.medical.medical.helper.PatientMapper;
import com.medical.medical.repository.CaregiverRepository;
import com.medical.medical.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CaregiverServiceImpl implements CaregiverService{
    private final CaregiverRepository caregiverRepository;
    private final PatientMapper patientMapper;

    private final PatientRepository patientRepository;

    @Override
    public List<PatientEntityDto> getPatients(Long caregiverId) {
        Optional<CaregiverEntity> care = caregiverRepository.findById(caregiverId);
        if (care.isEmpty()){
            throw new ServiceException("Caregiver doesn't exists!");
        }

        List<PatientEntityDto> patients = patientMapper.toDtos(care.get().getPatients());

        patients.forEach(r -> {
            r.setDoctorId(patientRepository.findById(r.getId()).get().getDoctorEntity().getId());
        });

        return patients;
    }
}
