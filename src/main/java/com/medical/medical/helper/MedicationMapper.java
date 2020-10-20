package com.medical.medical.helper;

import com.medical.medical.dtos.MedicationDto;
import com.medical.medical.entities.MedicationEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicationMapper {
    MedicationEntity toEntity(MedicationDto medicationDto);
    MedicationDto toDto(MedicationEntity medicationEntity);

    List<MedicationDto> toDtos(List<MedicationEntity> medicationEntities);
}
