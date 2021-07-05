package com.lab4.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id"),
        }
)
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User client;

    @Column
    @Min(1)
    @Max(100)
    private Long percent;
}
