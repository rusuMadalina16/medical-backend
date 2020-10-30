package com.medical.medical.service;

import com.github.javafaker.Faker;
import com.medical.medical.dtos.*;
import com.medical.medical.entities.*;
import com.medical.medical.helper.CaregiverMapper;
import com.medical.medical.helper.DoctorMapper;
import com.medical.medical.helper.MedicationMapper;
import com.medical.medical.helper.PatientMapper;
import com.medical.medical.repository.*;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService{
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final CaregiverRepository caregiverRepository;
    private final MedicationRepository medicationRepository;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

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

        System.out.println(entity.toString());
        Optional<DoctorEntity> doc = doctorRepository.findById(patientEntityDto.getDoctorId());
        if(doc.isEmpty()){
            throw new ServiceException("Doctor does not exists!");
        }
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

        List<PlanEntity> plans = planRepository.findAllByPatientId(id);

        plans.forEach(plan -> planRepository.deleteById(plan.getId()));

        if (receiverEntity.isEmpty())
            throw new ServiceException("No patient with that ID exists");

        patientRepository.deleteById(id);
    }

    @Override
    public List<PatientDtoCare> getPatients() {
        return patientMapper.toDtos2(patientRepository.findAll());
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
        planEntity.setInterval(planDto.getInterval());

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
    public void updatePatient(PatientDtoCare patientDtoCare) {
        Optional<PatientEntity> cat = patientRepository.findById(patientDtoCare.getId());
        if (cat.isEmpty())
            throw new ServiceException("No medication with that ID");

        List<PlanEntity> meds = planRepository.findAllByPatientId(patientDtoCare.getId());

        PatientEntity pat = patientMapper.toEntity3(patientDtoCare);

       // if (patientDtoCare.getCaregiverId()!=null){
            pat.setCaregiverEntity(cat.get().getCaregiverEntity());
        //}

        //if (patientDtoCare.getDoctorId()!=null){
            pat.setDoctorEntity(cat.get().getDoctorEntity());
        //}

        patientRepository.save(pat);

        updatePlanAgain(meds);
    }

    @Override
    public void updateCaregiver(CaregiverDto caregiverDto) {
        Optional<CaregiverEntity> cat = caregiverRepository.findById(caregiverDto.getId());
        if (cat.isEmpty())
            throw new ServiceException("No medication with that ID");
        List<PatientEntity> pats = cat.get().getPatients();
        CaregiverEntity care = caregiverMapper.toEntity(caregiverDto);
        care.setPatients(pats);
        caregiverRepository.save(care);
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

    @Override
    public List<CaregiverDto> getCaregiverByName(String name) {
        List<CaregiverEntity> caregiverEntities = caregiverRepository.findByName(name);
        return caregiverMapper.toDtos(caregiverEntities);
    }

    @Override
    public void updatePatientCare(PatientDtoCare patientDtoCare) {
        Optional<PatientEntity> cat = patientRepository.findById(patientDtoCare.getId());
        if (cat.isEmpty())
            throw new ServiceException("No patient with that ID");

        Optional<CaregiverEntity> care = caregiverRepository.findById(patientDtoCare.getCaregiverId());
        if (care.isEmpty())
            throw new ServiceException("No caregiver with that ID");

        Optional<DoctorEntity> doc = doctorRepository.findById(patientDtoCare.getDoctorId());
        if (doc.isEmpty())
            throw new ServiceException("No doctor with that ID");

        PatientEntity patientEntity = patientMapper.toEntity3(patientDtoCare);

        patientEntity.setCaregiverEntity(care.get());
        patientEntity.setDoctorEntity(doc.get());

        patientRepository.save(patientEntity);

    }

    @Override
    public void updatePlanAgain(List<PlanEntity> meds) {

        for(PlanEntity planEntity:meds){
            PlanEntity planEntity1 = new PlanEntity();
            planEntity1.setPatientId(planEntity.getPatientId());
            planEntity1.setStopDate(planEntity.getStopDate());
            planEntity1.setStartDate(planEntity.getStartDate());
            planEntity1.setMedicationId(planEntity.getMedicationId());
            planEntity1.setDoctorDosage(planEntity.getDoctorDosage());
            planEntity1.setInterval(planEntity.getInterval());
            planRepository.save(planEntity1);
        }
    }

    @Override
    public List<PatientDtoCare> getPatientsByDoctorId(Long doctorId) {
        Optional<DoctorEntity> doc = doctorRepository.findById(doctorId);

        if (doc.isEmpty()){
            throw new ServiceException("Doctor does not exists!");
        }

        List<PatientEntity> patientEntities = doc.get().getPatients();

        return patientEntities.stream().map(r -> {
            PatientDtoCare patientDtoCare = patientMapper.toDto2(r);
            patientDtoCare.setDoctorId(doctorId);
            if (r.getCaregiverEntity()!=null) {
                patientDtoCare.setCaregiverId(r.getCaregiverEntity().getId());
            }
            return patientDtoCare;
        })
        .collect(Collectors.toList());
    }

    @Override
    public void addUser(PatientEntityDto patientEntityDto) {
        PatientEntity patientEntity = patientRepository.findByNameAndBirthDateAndGenderAndAddressAndMedicalRecord(
                patientEntityDto.getName(),patientEntityDto.getBirthDate(),patientEntityDto.getGender(),
                patientEntityDto.getAddress(),patientEntityDto.getMedicalRecord()
        );

        UserEntity userEntity=new UserEntity();
        userEntity.setClientId(patientEntity.getId());
        userEntity.setPassword(generateRandomPassword());
        Faker faker=new Faker();
        userEntity.setUsername(faker.superhero().prefix()+faker.name().firstName()+faker.address().buildingNumber());
        userEntity.setRole("PATIENT");

        userRepository.save(userEntity);
    }

    @Override
    public void addUser2(CaregiverDto caregiverDto) {
        CaregiverEntity caregiverEntity = caregiverRepository.findByNameAndBirthDateAndGenderAndAddress(
                caregiverDto.getName(),caregiverDto.getBirthDate(),caregiverDto.getGender(),
                caregiverDto.getAddress());

        UserEntity userEntity=new UserEntity();
        userEntity.setClientId(caregiverEntity.getId());
        userEntity.setPassword(generateRandomPassword());
        Faker faker=new Faker();
        userEntity.setUsername(faker.superhero().prefix()+faker.name().firstName()+faker.address().buildingNumber());
        userEntity.setRole("CAREGIVER");

        userRepository.save(userEntity);
    }

    public String generateRandomPassword() {

        List<CharacterRule> rules = Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1), new CharacterRule(EnglishCharacterData.Digit, 1));

        PasswordGenerator generator = new PasswordGenerator();
        return generator.generatePassword(12, rules);
    }


}
