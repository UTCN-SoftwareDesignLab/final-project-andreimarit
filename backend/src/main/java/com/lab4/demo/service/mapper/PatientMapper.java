package com.lab4.demo.service.mapper;

import com.lab4.demo.model.Patient;
import com.lab4.demo.dto.PatientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDTO toDto(Patient patient);

    Patient fromDto(PatientDTO patient);

}
