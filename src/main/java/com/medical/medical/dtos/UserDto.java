package com.medical.medical.dtos;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String role;
    private Long clientId;
}
