package com.lab4.demo;

import com.lab4.demo.dto.DiscountDTO;
import com.lab4.demo.dto.ProductDTO;
import com.lab4.demo.model.ERole;
import com.lab4.demo.model.Role;
import com.lab4.demo.repo.RoleRepository;
import com.lab4.demo.repo.UserRepository;
import com.lab4.demo.service.DiscountService;
import com.lab4.demo.security.AuthService;
import com.lab4.demo.security.dto.SignupRequest;
import com.lab4.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;


    private final ProductService productService;

    private final DiscountService discountService;


    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {

            userRepository.deleteAll();
            roleRepository.deleteAll();


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
                    .roles(Set.of("EMPLOYEE"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("cineva@email.com")
                    .username("cineva")
                    .password("Cineva!123")
                    .roles(Set.of("EMPLOYEE"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("altcineva@email.com")
                    .username("altcineva")
                    .password("Altcineva!123")
                    .roles(Set.of("EMPLOYEE"))
                    .build());

            authService.register(SignupRequest.builder()
                    .email("client@email.com")
                    .username("client")
                    .password("Client!123")
                    .roles(Set.of("CLIENT"))
                    .build());

            productService.create(ProductDTO.builder()
                    .name("papuci")
                    .price(10L)
                    .quantity(10L)
                    .build());

            productService.create(ProductDTO.builder()
                    .name("telefon")
                    .price(400L)
                    .quantity(5L)
                    .build());

            productService.create(ProductDTO.builder()
                    .name("suc")
                    .price(1L)
                    .quantity(50L)
                    .build());

            discountService.create(DiscountDTO.builder()
                    .client(5L)
                    .percent(50L)
                    .build());



//            PatientDTO patientDTO = new PatientDTO(1L, "patientStart", "12345");
//
//            patientService.create(patientDTO);
//
//            patientDTO = new PatientDTO(2L, "patientSecond", "123123");
//
//            patientService.create(patientDTO);
//
//                ConsultationDTO consultationDTO = new ConsultationDTO(1L, "2021/07/07 15:00",1L, 2L);
//                consultationService.create(consultationDTO);



        }
    }
}
