package com.lab4.demo.service;

import com.lab4.demo.dto.ConsultationDTO;
import com.lab4.demo.model.Consultation;
import com.lab4.demo.model.Patient;
import com.lab4.demo.model.User;
import com.lab4.demo.repo.ConsultationRepository;
import com.lab4.demo.repo.PatientRepository;
import com.lab4.demo.repo.UserRepository;
import com.lab4.demo.service.mapper.ConsultationMapper;
import com.lab4.demo.service.mapper.ConsultationMapperImplementation;
import com.lab4.demo.websocket.WsServerEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final ConsultationMapperImplementation consultationMapper;

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public List<ConsultationDTO> findAll(){
        return consultationRepository.findAll().stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    public Consultation findById(Long id){
        return consultationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consultation not found: " + id));
    }

    public void deleteById(Long id){
        WsServerEndpoint.sendAlert("Consultation " + id + "has been deleted");
        consultationRepository.deleteById(id);
    }

    public ConsultationDTO create(ConsultationDTO consultationDTO){

        User doctor = userRepository.findById( consultationDTO.getDoctor()).orElseThrow(() -> new EntityNotFoundException("Doctor NOT FOUND!!"));
        Patient patient = patientRepository.findById(consultationDTO.getPatient()).orElseThrow(() -> new EntityNotFoundException("Patient NOT FOUND!!!"));

        Consultation consultation = Consultation.builder()
                    .doctor(doctor)
                    .patient(patient)
                    .date(consultationDTO.getDate())
                    .build();
        WsServerEndpoint.sendAlert("A consultation has been created, for Patient " + patient.getName() + " with Doctor Dr. " +
                doctor.getUsername() + "on " + consultation.getDate());
            return consultationMapper.toDto(consultationRepository.save(consultation));

    }

    public ConsultationDTO edit(Long id, ConsultationDTO consultationDTO){
        Consultation consultation = consultationMapper.fromDto(consultationDTO);
        consultation.setId(id);
        WsServerEndpoint.sendAlert("Consultation " + consultation.getId() + " has been edited");
        return consultationMapper.toDto(consultationRepository.save(consultation));
    }
}