package com.medical.medical.dtos;

import lombok.Data;

@Data
public class PlanDto {
    private Long id;
    private String dataStart;
    private String dataStop;
    private String doctorDosage;
    private Long idMedication;
    private Long idPatient;
}
