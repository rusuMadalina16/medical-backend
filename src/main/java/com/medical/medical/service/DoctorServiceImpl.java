package com.medical.medical.service;

import com.medical.medical.dtos.DoctorDto;
import com.medical.medical.dtos.PatientEntityDto;
import com.medical.medical.entities.DoctorEntity;
import com.medical.medical.entities.PatientEntity;
import com.medical.medical.helper.DoctorMapper;
import com.medical.medical.repository.DoctorRepository;
import com.medical.medical.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService{
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    private final DoctorMapper doctorMapper;

    @Override
    public void addPatient(PatientEntityDto patientEntityDto) {

        PatientEntity entity = new PatientEntity();
        entity.setAddress(patientEntityDto.getAddress());
        entity.setBirthDate(patientEntityDto.getBirthDate());
        entity.setGender(patientEntityDto.getGender());
        entity.setMedicalRecord(patientEntityDto.getMedicalRecord());
        entity.setName(patientEntityDto.getName());

        Optional<DoctorEntity> doc = doctorRepository.findById(patientEntityDto.getDoctorId());

        if (doc.isEmpty())
            throw new ServiceException("Doctor does not exists!");

        entity.setDoctorEntity(doc.get());

        patientRepository.save(entity);
    }

    @Override
    public DoctorDto getDoctorById(Long doctorId) {
        Optional<DoctorEntity> doc = doctorRepository.findById(doctorId);

        if (doc.isEmpty()){
            throw new ServiceException("Doctor does not exists!");
        }

        return doctorMapper.toDto(doc.get());
    }
}
