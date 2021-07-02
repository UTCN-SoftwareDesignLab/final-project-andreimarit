package com.lab4.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id"),
                @UniqueConstraint(columnNames = "phoneNumber")
        }
)
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String phoneNumber;

}
