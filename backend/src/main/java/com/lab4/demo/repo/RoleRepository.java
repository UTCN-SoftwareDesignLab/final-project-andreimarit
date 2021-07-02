package com.lab4.demo.repo;

import com.lab4.demo.model.ERole;
import com.lab4.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role);
}
