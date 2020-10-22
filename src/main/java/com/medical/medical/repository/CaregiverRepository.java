package com.medical.medical.repository;

import com.medical.medical.entities.CaregiverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaregiverRepository extends JpaRepository<CaregiverEntity, Long> {
    List<CaregiverEntity> findByName(String name);
}
