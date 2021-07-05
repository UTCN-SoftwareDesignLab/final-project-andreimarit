package com.lab4.demo.dto;

import com.lab4.demo.model.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@RequiredArgsConstructor
@Data
@SuperBuilder
@Setter
@Builder
public class UserString {

    private Long id;
    private String email;
    private String username;
    private String password;
    private String role;
    private Long wallet;
}
