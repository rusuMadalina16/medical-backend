package com.medical.medical.service;

import com.medical.medical.dtos.CaregiverDto;
import com.medical.medical.dtos.DoctorDto;
import com.medical.medical.dtos.PatientEntityDto;
import com.medical.medical.entities.CaregiverEntity;
import com.medical.medical.entities.DoctorEntity;
import com.medical.medical.entities.PatientEntity;
import com.medical.medical.helper.CaregiverMapper;
import com.medical.medical.helper.DoctorMapper;
import com.medical.medical.helper.PatientMapper;
import com.medical.medical.repository.CaregiverRepository;
import com.medical.medical.repository.DoctorRepository;
import com.medical.medical.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService{
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final CaregiverRepository caregiverRepository;

    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;
    private final CaregiverMapper caregiverMapper;

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

    @Override
    public void deletePatientById(Long id) {
        Optional<PatientEntity> receiverEntity = patientRepository.findById(id);

        if (receiverEntity.isEmpty())
            throw new ServiceException("No patient with that ID exists");

        patientRepository.deleteById(id);
    }

    @Override
    public List<PatientEntityDto> getPatients(Long doctorId) {
        Optional<DoctorEntity> doctorEntity = doctorRepository.findById(doctorId);

        if (doctorEntity.isEmpty())
            throw new ServiceException("No doctor with that ID exists");

        return patientMapper.toDtos(doctorEntity.get().getPatients());
    }

    @Override
    public void addCaregiver(CaregiverDto caregiverDto) {
        CaregiverEntity entity = new CaregiverEntity();
        entity.setAddress(caregiverDto.getAddress());
        entity.setBirthDate(caregiverDto.getBirthDate());
        entity.setGender(caregiverDto.getGender());
        entity.setName(caregiverDto.getName());

        caregiverRepository.save(entity);
    }

    @Override
    public List<CaregiverDto> getCaregivers() {
        return caregiverMapper.toDtos(caregiverRepository.findAll());
    }

    @Override
    public void deleteCaregiverById(Long id) {
        Optional<CaregiverEntity> caregiverEntity = caregiverRepository.findById(id);

        if (caregiverEntity.isEmpty())
            throw new ServiceException("No patient with that ID exists");

        caregiverRepository.deleteById(id);
    }
}
