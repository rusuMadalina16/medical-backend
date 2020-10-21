package com.medical.medical.controller;

import com.medical.medical.dtos.*;
import com.medical.medical.entities.PlanEntity;
import com.medical.medical.repository.PlanRepository;
import com.medical.medical.service.DoctorService;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@CrossOrigin
@AllArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private final PlanRepository planRepository;

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

    @GetMapping("/get-med/{name}")
    public ResponseEntity<?> getMedsByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok().body(doctorService.getMedsByName(name));
        }
        catch(ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/ppatient/{name}")
    public ResponseEntity<?> getPatientByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok().body(doctorService.getPatientByName(name));
        }
        catch(ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/ccare/{name}")
    public ResponseEntity<?> getCaregiverByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok().body(doctorService.getCaregiverByName(name));
        }
        catch(ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-patient/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(doctorService.getPatientById(id));
        }
        catch(ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/get-caregiver/{id}")
    public ResponseEntity<?> getCaregiverById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(doctorService.getCaregiverById(id));
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

    @GetMapping("/get-patients")
    public ResponseEntity<?> getPatients() {
        try {
            return ResponseEntity.ok().body(doctorService.getPatients());
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

    @PostMapping("/add-medication")
    public ResponseEntity<String> addMedication(@RequestBody MedicationDto medicationDto) {
        try {
            doctorService.addMedication(medicationDto);
            return ResponseEntity.ok().build();
        }
        catch (ServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-medications")
    public ResponseEntity<?> getMedications() {
        try {
            return ResponseEntity.ok().body(doctorService.getMedications());
        }
        catch(ServiceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete-medication/{id}")
    public ResponseEntity<?> deleteMedicationById(@PathVariable Long id) {
        try {
            doctorService.deleteMedicationById(id);
            return ResponseEntity.ok().build();
        }
        catch (ServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-plan")
    public ResponseEntity<String> addPlan(@RequestBody PlanDto planDto) {
        try {
            doctorService.addPlan(planDto);
            return ResponseEntity.ok().build();
        }
        catch (ServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-medication")
    public ResponseEntity<?> updateCategory(@RequestBody MedicationDto medicationDto){
        try{
            doctorService.updateMedication(medicationDto);
            return ResponseEntity.ok().build();
        }
        catch (ServiceException e) {
            return ResponseEntity.badRequest().body("No such category");
        }
    }

    @PutMapping("/update-patient")
    public ResponseEntity<?> updatePatient(@RequestBody PatientEntityDto patientEntityDto){
        try{
            doctorService.updatePatient(patientEntityDto);
            return ResponseEntity.ok().build();
        }
        catch (ServiceException e) {
            return ResponseEntity.badRequest().body("No such category");
        }
    }

    @PutMapping("/update-caregiver")
    public ResponseEntity<?> updateCaregiver(@RequestBody CaregiverDto caregiverDto){
        try{
            doctorService.updateCaregiver(caregiverDto);
            return ResponseEntity.ok().build();
        }
        catch (ServiceException e) {
            return ResponseEntity.badRequest().body("No such category");
        }
    }

    @PutMapping("/update-patient-caregiver")
    public ResponseEntity<?> updatePatientCaregiver(@RequestBody PatientDtoCare patientDtoCare){
        try{
            List<PlanEntity> meds = planRepository.findAllByPatientId(patientDtoCare.getId());
            doctorService.updatePatientCare(patientDtoCare);
            doctorService.updatePlanAgain(meds);
            return ResponseEntity.ok().build();
        }
        catch (ServiceException e) {
            return ResponseEntity.badRequest().body("No such category");
        }
    }
}
