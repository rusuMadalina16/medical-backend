package com.medical.medical.dtos;

import lombok.Data;

@Data
public class FullDetailPlanDto {
    private final String name;
    private final String sideEffects;
    private final String prospectDosage;
    private final String doctorDosage;
    private final String dataStart;
    private final String dataStop;
    private final String interval;
}
