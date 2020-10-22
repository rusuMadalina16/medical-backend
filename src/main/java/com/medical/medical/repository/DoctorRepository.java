package com.medical.medical.repository;

import com.medical.medical.entities.DoctorEntity;
import com.medical.medical.entities.MedicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
}
