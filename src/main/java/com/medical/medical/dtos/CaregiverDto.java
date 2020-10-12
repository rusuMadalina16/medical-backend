package com.medical.medical.dtos;

import lombok.Data;

@Data
public class CaregiverDto {
    private Long id;
    private String name;
    private String birthDate;
    private String gender;
    private String address;
}
