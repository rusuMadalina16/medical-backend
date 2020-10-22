package com.medical.medical.controller;

import com.medical.medical.dtos.LogInDto;
import com.medical.medical.service.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/log-in")
@CrossOrigin
@AllArgsConstructor
public class LogInController {
    private final UserService userService;

    @GetMapping("/{username}/{password}")
    public ResponseEntity<?> logIn(@PathVariable String username,
                                   @PathVariable String password) {
        try {
            return ResponseEntity.ok().body(userService.logIn(username, password));
        }
        catch(ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
