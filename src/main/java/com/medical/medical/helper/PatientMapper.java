package com.medical.medical.helper;

import com.medical.medical.dtos.PatientEntityDto;
import com.medical.medical.entities.PatientEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientEntity toEntity(PatientEntityDto patientEntityDto);
    PatientEntityDto toDto(PatientEntity patientEntity);

    List<PatientEntityDto> toDtos(List<PatientEntity> patientEntities);
}
