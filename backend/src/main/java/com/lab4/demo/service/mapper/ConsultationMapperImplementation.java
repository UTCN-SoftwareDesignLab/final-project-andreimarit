package com.lab4.demo.service.mapper;


import com.lab4.demo.dto.ConsultationDTO;
import com.lab4.demo.model.Consultation;
import com.lab4.demo.model.Patient;
import com.lab4.demo.model.User;
import com.lab4.demo.repo.ConsultationRepository;
import com.lab4.demo.repo.PatientRepository;
import com.lab4.demo.repo.UserRepository;
import com.lab4.demo.websocket.WsServerEndpoint;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
public class ConsultationMapperImplementation implements ConsultationMapper {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    @Override
    public ConsultationDTO toDto(Consultation consultation) {
        if (consultation == null) {
            return null;
        }



        ConsultationDTO consultationDTO = new ConsultationDTO();

        consultationDTO.setId(consultation.getId());
        consultationDTO.setPatient(consultation.getPatient().getId());
        consultationDTO.setDoctor(consultation.getDoctor().getId());
        consultationDTO.setDate(consultation.getDate());

        return consultationDTO;
    }

    @Override
    public Consultation fromDto(ConsultationDTO consultationDTO) {
        if (consultationDTO == null) {
            return null;
        }

        Consultation consultation = new Consultation();

        consultation.setId(consultationDTO.getId());

        User doctor = userRepository.findById( consultationDTO.getDoctor()).orElseThrow(() -> new EntityNotFoundException("Doctor NOT FOUND!!"));
        Patient patient = patientRepository.findById(consultationDTO.getPatient()).orElseThrow(() -> new EntityNotFoundException("Patient NOT FOUND!!!"));

        consultation.setPatient(patient);
        consultation.setDoctor(doctor);
        consultation.setDate(consultationDTO.getDate());

        return consultation;
    }
}