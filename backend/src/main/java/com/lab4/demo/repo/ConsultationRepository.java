package com.lab4.demo.repo;

import com.lab4.demo.model.Consultation;
import com.lab4.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation,Long> {

    Consultation findByDate(String date);
}
