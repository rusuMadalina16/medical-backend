package com.medical.medical.helper;

import com.medical.medical.dtos.DoctorDto;
import com.medical.medical.entities.DoctorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    DoctorEntity toEntity(DoctorDto doctorDto);
    DoctorDto toDto(DoctorEntity doctorEntity);
}
