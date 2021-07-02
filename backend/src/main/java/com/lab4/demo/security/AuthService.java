package com.lab4.demo.security;

import com.lab4.demo.security.dto.SignupRequest;
import com.lab4.demo.repo.RoleRepository;
import com.lab4.demo.repo.UserRepository;
import com.lab4.demo.model.ERole;
import com.lab4.demo.model.Role;
import com.lab4.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;


    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void register(SignupRequest signUpRequest) {
        User user = User.builder()
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .build();

        Set<String> rolesStr = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (rolesStr == null) {
            Role defaultRole = roleRepository.findByName(ERole.DOCTOR)
                    .orElseThrow(() -> new RuntimeException("Cannot find DOCTOR role"));
            roles.add(defaultRole);
        } else {
            rolesStr.forEach(r -> {
                Role ro = roleRepository.findByName(ERole.valueOf(r))
                        .orElseThrow(() -> new RuntimeException("Cannot find role: " + r));
                roles.add(ro);
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
    }
}
