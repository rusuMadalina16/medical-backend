package com.medical.medical.repository;


import com.medical.medical.entities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    List<PatientEntity> findByName(String name);
    PatientEntity findByNameAndBirthDateAndGenderAndAddressAndMedicalRecord(
            String name, String birthDate, String gender, String address, String medicalRecord
    );
}
