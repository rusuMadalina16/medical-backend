package com.medical.medical.helper;

import com.medical.medical.dtos.PatientEntityDto;
import com.medical.medical.dtos.PlanDto;
import com.medical.medical.entities.PatientEntity;
import com.medical.medical.entities.PlanEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlanMapper {

    PlanEntity toEntity(PlanDto planDto);
    PlanDto toDto(PlanEntity planEntity);

    List<PlanDto> toDtos(List<PlanEntity> planEntities);
}
