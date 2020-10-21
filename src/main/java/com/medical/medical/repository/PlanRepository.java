package com.medical.medical.repository;

import com.medical.medical.entities.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, Long> {
    List<PlanEntity> findAllByMedicationId(Long id);

    List<PlanEntity> findAllByPatientId(Long id);
}
