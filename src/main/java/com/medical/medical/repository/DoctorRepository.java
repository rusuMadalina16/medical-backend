package com.medical.medical.repository;

import com.medical.medical.entities.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
    DoctorEntity findByNameAndBirthDateAndGenderAndAddress(String name, String birthDate, String gender, String address);
}
