package com.medical.medical.service;

import com.medical.medical.dtos.*;
import com.medical.medical.entities.*;
import com.medical.medical.helper.CaregiverMapper;
import com.medical.medical.helper.DoctorMapper;
import com.medical.medical.helper.MedicationMapper;
import com.medical.medical.helper.PatientMapper;
import com.medical.medical.repository.*;
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
    private final MedicationRepository medicationRepository;
    private final PlanRepository planRepository;

    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;
    private final CaregiverMapper caregiverMapper;
    private final MedicationMapper medicationMapper;

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
            throw new ServiceException("No caregiver with that ID exists");

        caregiverRepository.deleteById(id);
    }

    @Override
    public void addMedication(MedicationDto medicationDto) {
        MedicationEntity entity = new MedicationEntity();
        entity.setDosage(medicationDto.getDosage());
        entity.setSideEffects(medicationDto.getSideEffects());
        entity.setName(medicationDto.getName());

        medicationRepository.save(entity);
    }

    @Override
    public List<MedicationDto> getMedications() {
        return medicationMapper.toDtos(medicationRepository.findAll());
    }

    @Override
    public void deleteMedicationById(Long id) {
        Optional<MedicationEntity> medicationEntity = medicationRepository.findById(id);

        if (medicationEntity.isEmpty())
            throw new ServiceException("No medication with that ID exists");

        medicationRepository.deleteById(id);
    }

    @Override
    public void addPlan(PlanDto planDto) {
        PlanEntity planEntity = new PlanEntity();
        planEntity.setDoctorDosage(planDto.getDoctorDosage());
        planEntity.setMedicationId(planDto.getIdMedication());
        planEntity.setPatientId(planDto.getIdPatient());
        planEntity.setStartDate(planDto.getDataStart());
        planEntity.setStopDate(planDto.getDataStop());

        planRepository.save(planEntity);
    }
}
