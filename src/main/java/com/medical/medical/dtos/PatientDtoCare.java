package com.medical.medical.dtos;

import lombok.Data;

@Data
public class PatientDtoCare {
    private Long id;
    private String name;
    private String birthDate;
    private String gender;
    private String address;
    private String medicalRecord;
    private String interval;
    private Long doctorId;
    private Long caregiverId;
}
