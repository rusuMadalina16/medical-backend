package com.medical.medical.service;

import com.medical.medical.dtos.UserDto;

public interface UserService {
    UserDto logIn(String username, String password);
}
