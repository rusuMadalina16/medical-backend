package com.medical.medical.service;

import com.medical.medical.dtos.FullDetailPlanDto;
import com.medical.medical.dtos.PatientDtoCare;
import com.medical.medical.dtos.PlanDto;
import com.medical.medical.entities.MedicationEntity;
import com.medical.medical.entities.PatientEntity;
import com.medical.medical.entities.PlanEntity;
import com.medical.medical.helper.PatientMapper;
import com.medical.medical.helper.PlanMapper;
import com.medical.medical.repository.MedicationRepository;
import com.medical.medical.repository.PatientRepository;
import com.medical.medical.repository.PlanRepository;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService{
    private final PatientMapper patientMapper;
    private final PlanMapper planMapper;

    private final PatientRepository patientRepository;
    private final PlanRepository planRepository;
    private final MedicationRepository medicationRepository;

    @Override
    public PatientDtoCare getPatientById(Long id) {
        Optional<PatientEntity> patient = patientRepository.findById(id);

        if(patient.isEmpty()){
            throw new ServiceException("Patient does not exist!");
        }

        PatientDtoCare patientDtoCare = patientMapper.toDto2(patient.get());
        if (patient.get().getDoctorEntity()!=null)
        patientDtoCare.setDoctorId(patient.get().getDoctorEntity().getId());

        if (patient.get().getCaregiverEntity()!=null)
        patientDtoCare.setCaregiverId(patient.get().getCaregiverEntity().getId());

        return patientDtoCare;
    }

    @Override
    public List<FullDetailPlanDto> getPlansByPatientId(Long id) {
        List<PlanEntity> plans = planRepository.findAllByPatientId(id);
        List<PlanDto> planDtos = planMapper.toDtos(plans);
        planDtos.forEach(r->{
            r.setDataStart(plans.stream().filter(g-> g.getId().equals(r.getId())).collect(Collectors.toList()).get(0)
            .getStartDate());
            r.setDataStop(plans.stream().filter(g-> g.getId().equals(r.getId())).collect(Collectors.toList()).get(0)
                    .getStopDate());
            r.setIdMedication(plans.stream().filter(g-> g.getId().equals(r.getId())).collect(Collectors.toList()).get(0)
                    .getMedicationId());
            r.setIdPatient(id);
        });
        List<PlanDto> planList = planDtos.stream().sorted(Comparator.comparing(PlanDto::getIdMedication)).collect(Collectors.toList());

        List<FullDetailPlanDto> returnList = new ArrayList<>();
        planList.forEach(r->{
            Optional<MedicationEntity> med = medicationRepository.findById(r.getIdMedication());

            if (med.isEmpty()){
                throw new ServiceException("Med does not exist!");
            }

            FullDetailPlanDto fullDetailPlanDto = new FullDetailPlanDto(
                    med.get().getName(),
                    med.get().getSideEffects(),
                    med.get().getDosage(),
                    r.getDoctorDosage(),
                    r.getDataStart(),
                    r.getDataStop(),
                    r.getInterval()
            );
            returnList.add(fullDetailPlanDto);

        });
        return returnList;
    }
}
