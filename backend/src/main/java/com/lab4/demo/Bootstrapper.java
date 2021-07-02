package com.lab4.demo;

import com.lab4.demo.dto.ConsultationDTO;
import com.lab4.demo.dto.PatientDTO;
import com.lab4.demo.model.ERole;
import com.lab4.demo.model.Role;
import com.lab4.demo.repo.ConsultationRepository;
import com.lab4.demo.repo.PatientRepository;
import com.lab4.demo.repo.RoleRepository;
import com.lab4.demo.repo.UserRepository;
import com.lab4.demo.service.ConsultationService;
import com.lab4.demo.service.PatientService;
import com.lab4.demo.security.AuthService;
import com.lab4.demo.security.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final PatientService patientService;

    private final ConsultationService consultationService;

    private final PatientRepository patientRepository;

    private final ConsultationRepository consultationRepository;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            patientRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            consultationRepository.deleteAll();

            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("andrei@email.com")
                    .username("andrei")
                    .password("Andrei!123")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("doctor@email.com")
                    .username("doctor")
                    .password("Doctor!123")
                    .roles(Set.of("DOCTOR"))
                    .build());

            PatientDTO patientDTO = new PatientDTO(1L, "patientStart", "12345");

            patientService.create(patientDTO);

            patientDTO = new PatientDTO(2L, "patientSecond", "123123");

            patientService.create(patientDTO);

                ConsultationDTO consultationDTO = new ConsultationDTO(1L, "2021/07/07 15:00",1L, 2L);
                consultationService.create(consultationDTO);

        }
    }
}
