package com.medical.medical.controller;

import com.medical.medical.dtos.CaregiverDto;
import com.medical.medical.dtos.PatientEntityDto;
import com.medical.medical.service.DoctorService;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
@CrossOrigin
@AllArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping("/add-patient")
    public ResponseEntity<String> addReceiver(@RequestBody PatientEntityDto patientEntityDto) {
        try {
            doctorService.addPatient(patientEntityDto);
            return ResponseEntity.ok().build();
        }
        catch (ServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-doctor/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(doctorService.getDoctorById(id));
        }
        catch(ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-patient/{id}")
    public ResponseEntity<?> deleteReceiverById(@PathVariable Long id) {
        try {
            doctorService.deletePatientById(id);
            return ResponseEntity.ok().build();
        }
        catch (ServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-patients/{doctorId}")
    public ResponseEntity<?> getPatients(@PathVariable Long doctorId) {
        try {
            return ResponseEntity.ok().body(doctorService.getPatients(doctorId));
        }
        catch(ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add-caregiver")
    public ResponseEntity<String> addCaregiver(@RequestBody CaregiverDto caregiverDto) {
        try {
            doctorService.addCaregiver(caregiverDto);
            return ResponseEntity.ok().build();
        }
        catch (ServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-caregivers")
    public ResponseEntity<?> getCaregivers() {
        try {
            return ResponseEntity.ok().body(doctorService.getCaregivers());
        }
        catch(ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-caregiver/{id}")
    public ResponseEntity<?> deleteCaregiverById(@PathVariable Long id) {
        try {
            doctorService.deleteCaregiverById(id);
            return ResponseEntity.ok().build();
        }
        catch (ServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
