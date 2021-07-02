package com.lab4.demo.service.mapper;

import com.lab4.demo.dto.ConsultationDTO;
import com.lab4.demo.model.Consultation;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface ConsultationMapper {

    ConsultationDTO toDto(Consultation consultation);

    Consultation fromDto(ConsultationDTO consultationDto);
}
