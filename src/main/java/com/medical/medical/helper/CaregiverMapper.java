package com.medical.medical.helper;

import com.medical.medical.dtos.CaregiverDto;
import com.medical.medical.dtos.PatientEntityDto;
import com.medical.medical.entities.CaregiverEntity;
import com.medical.medical.entities.PatientEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CaregiverMapper {

    CaregiverEntity toEntity(CaregiverDto caregiverDto);
    CaregiverDto toDto(CaregiverEntity caregiverEntity);

    List<CaregiverDto> toDtos(List<CaregiverEntity> caregiverEntities);
}
