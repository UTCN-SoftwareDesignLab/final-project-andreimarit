package com.lab4.demo.dto;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsultationDTO {

    private Long id;
    private String date;
    private Long patient;
    private Long doctor;
}
