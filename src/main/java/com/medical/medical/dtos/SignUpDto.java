package com.medical.medical.dtos;

import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String birthDate;
    private String gender;
    private String address;
    private String username;
    private String password;
}
