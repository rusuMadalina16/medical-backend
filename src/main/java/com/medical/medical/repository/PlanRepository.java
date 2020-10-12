package com.medical.medical.repository;

import com.medical.medical.dtos.PlanDto;
import com.medical.medical.entities.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, Long> {
}
