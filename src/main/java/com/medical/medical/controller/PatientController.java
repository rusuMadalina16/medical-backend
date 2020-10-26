package com.medical.medical.controller;

import com.medical.medical.service.PatientService;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
@CrossOrigin
@AllArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping("/get-patient/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(patientService.getPatientById(id));
        }
        catch(ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-plans/{patientId}")
    public ResponseEntity<?> getPlansByPatientId(@PathVariable Long patientId) {
        try {
            return ResponseEntity.ok().body(patientService.getPlansByPatientId(patientId));
        }
        catch(ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
