package com.lab4.demo.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String date;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User doctor;

}
