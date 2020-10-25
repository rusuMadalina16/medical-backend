package com.medical.medical.controller;

import com.medical.medical.service.CaregiverService;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/caregiver")
@CrossOrigin
@AllArgsConstructor
public class CaregiverController {
    private final CaregiverService caregiverService;

    @GetMapping("/get-patients/{caregiverId}")
    public ResponseEntity<?> getPatients(@PathVariable Long caregiverId) {
        try {
            return ResponseEntity.ok().body(caregiverService.getPatients(caregiverId));
        }
        catch(ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-caregiver/{caregiverId}")
    public ResponseEntity<?> getCaregiverById(@PathVariable Long caregiverId) {
        try {
            return ResponseEntity.ok().body(caregiverService.getCaregiverById(caregiverId));
        }
        catch(ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
