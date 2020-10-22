package com.medical.medical.service;

import com.medical.medical.dtos.LogInDto;
import com.medical.medical.dtos.UserDto;
import com.medical.medical.entities.UserEntity;
import com.medical.medical.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto logIn(String username, String password) {
        UserEntity user = userRepository.findByUsernameAndPassword(username,password);

        Optional<UserEntity> user2 = userRepository.findById(user.getId());

        if (user2.isEmpty()){
            throw new ServiceException("User does not exists!");
        }

        UserDto userDto = new UserDto();
        userDto.setClientId(user2.get().getClientId());
        userDto.setId(user2.get().getId());
        userDto.setRole(user2.get().getRole());

        return userDto;
    }


}
