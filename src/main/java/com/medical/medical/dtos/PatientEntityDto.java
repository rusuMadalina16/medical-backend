package com.medical.medical.dtos;

import lombok.Data;

import javax.persistence.Column;

@Data
public class PatientEntityDto {
    private Long id;

    private String name;

    private String birthDate;

    private String gender;

    private String address;

    private String medicalRecord;

    private Long doctorId;
}
