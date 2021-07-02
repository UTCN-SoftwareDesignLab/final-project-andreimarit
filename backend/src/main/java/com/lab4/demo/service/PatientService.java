package com.lab4.demo.service;

import com.lab4.demo.model.Patient;
import com.lab4.demo.dto.PatientDTO;
import com.lab4.demo.repo.PatientRepository;
import com.lab4.demo.service.mapper.PatientMapper;
import com.lab4.demo.websocket.WsServerEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public List<PatientDTO> findAll() {
        return patientRepository.findAll().stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    private Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found: " + id));
    }

    public PatientDTO create(PatientDTO patient) {
        WsServerEndpoint.sendAlert("Patient " + patient.getName() + " joined our clinic");
        return patientMapper.toDto(patientRepository.save(
                patientMapper.fromDto(patient)
        ));
    }

    public PatientDTO edit(PatientDTO patient, Long id) {
        Patient actPatient = findById(id);
        actPatient.setId(patient.getId());
        actPatient.setName(patient.getName());
        actPatient.setPhoneNumber(patient.getPhoneNumber());
              return patientMapper.toDto(
                patientRepository.save(actPatient)
        );
    }

    public void delete(Long id){

        WsServerEndpoint.sendAlert("Patient with id " + id + " unsubscribed from our clinic");
        patientRepository.deleteById(id);
    }

//    public List<PatientDTO> findByNAG(String name,String author, String genre){
//        return patientRepository.findByNameContainingAndAuthorContainingAndGenreContainingIgnoreCase(name,author,genre)
//                .stream().map(patientMapper::toDto)
//                .collect(Collectors.toList());
//    }

//    public boolean sold(Long id, int quantity){
//        Patient patient = findById(id);
//        if(quantity > patient.getQuantity()){
//            patient.setQuantity(patient.getQuantity()-quantity);
//            patientRepository.save(patient);
//            return true;
//        }
//        else return false;
//    }
}
