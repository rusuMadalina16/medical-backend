package com.medical.medical.dtos;

import lombok.Data;

@Data
public class MedicationDto {
    private Long id;

    private String name;

    private String sideEffects;

    private String dosage;
}
