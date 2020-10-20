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
    public List<PatientEntityDto> getPatients() {
        return patientMapper.toDtos(patientRepository.findAll());
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

        List<PlanEntity> plans = planRepository.findAllByMedicationId(medicationEntity.get().getId());

        plans.forEach(planEntity -> planRepository.deleteById(planEntity.getId()));

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

    @Override
    public void updateMedication(MedicationDto medicationDto) {
        Optional<MedicationEntity> cat = medicationRepository.findById(medicationDto.getId());
        if (cat.isEmpty())
            throw new ServiceException("No medication with that ID");
        medicationRepository.save(medicationMapper.toEntity(medicationDto));
    }

    @Override
    public void updatePatient(PatientEntityDto patientEntityDto) {
        Optional<PatientEntity> cat = patientRepository.findById(patientEntityDto.getId());
        if (cat.isEmpty())
            throw new ServiceException("No medication with that ID");
        patientRepository.save(patientMapper.toEntity(patientEntityDto));
    }

    @Override
    public void updateCaregiver(CaregiverDto caregiverDto) {
        Optional<CaregiverEntity> cat = caregiverRepository.findById(caregiverDto.getId());
        if (cat.isEmpty())
            throw new ServiceException("No medication with that ID");
        caregiverRepository.save(caregiverMapper.toEntity(caregiverDto));
    }

    @Override
    public PatientEntityDto getPatientById(Long id) {
        Optional<PatientEntity> doc = patientRepository.findById(id);

        if (doc.isEmpty()){
            throw new ServiceException("Patient does not exists!");
        }

        return patientMapper.toDto(doc.get());
    }

    @Override
    public CaregiverDto getCaregiverById(Long id) {
        Optional<CaregiverEntity> doc = caregiverRepository.findById(id);

        if (doc.isEmpty()){
            throw new ServiceException("Caregiver does not exists!");
        }

        return caregiverMapper.toDto(doc.get());
    }

    @Override
    public List<MedicationDto> getMedsByName(String name) {
        List<MedicationEntity> medicationEntities=medicationRepository.findByName(name);
        return medicationMapper.toDtos(medicationEntities);
    }

    @Override
    public List<PatientEntityDto> getPatientByName(String name) {
        List<PatientEntity> patientEntities = patientRepository.findByName(name);
        return patientMapper.toDtos(patientEntities);
    }
}
