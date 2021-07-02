package com.lab4.demo.service.mapper;

import com.lab4.demo.dto.UserListDTO;
import com.lab4.demo.dto.UserMinimalDTO;
import com.lab4.demo.dto.UserString;
import com.lab4.demo.model.ERole;
import com.lab4.demo.model.Role;
import com.lab4.demo.model.User;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;

import com.lab4.demo.repo.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImplementation implements UserMapper {

    private final RoleRepository roleRepository;

    @Override
    public UserMinimalDTO userMinimalFromUser(User user) {
        if ( user == null ) {
            return null;
        }

        UserMinimalDTO userMinimalDTO = new UserMinimalDTO();

        userMinimalDTO.setUsername( user.getUsername() );
        userMinimalDTO.setId( user.getId() );

        return userMinimalDTO;
    }

    @Override
    public UserListDTO userListDtoFromUser(User user) {
        if ( user == null ) {
            return null;
        }

        String email = null;
        String password = null;

        email = user.getEmail();
        password = user.getPassword();

        Set<Role> role = null;

        UserListDTO userListDTO = new UserListDTO( email, role, password );

        userListDTO.setUsername( user.getUsername() );
        userListDTO.setId( user.getId() );

        populateRoles( user, userListDTO );

        return userListDTO;
    }

    @Override
    public UserString userStringFromUser(User user) {
        if ( user == null ) {
            return null;
        }

        UserString userString = new UserString();

        userString.setId( user.getId() );
        userString.setEmail( user.getEmail());
        userString.setRole(user.getRoles().stream().findFirst().get().getName().toString());

        //userString.setRole(ERole.DOCTOR.toString());
        return userString;
    }

    @Override
    public UserListDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        String email = null;
        String password = null;

        email = user.getEmail();
        password = user.getPassword();

        Set<Role> role = user.getRoles();

        UserListDTO userListDTO = new UserListDTO( email, role, password );

        userListDTO.setUsername( user.getUsername() );
        userListDTO.setId( user.getId() );

        populateRoles( user, userListDTO );

        return userListDTO;
    }

    @Override
    public User fromDTO(UserListDTO user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setUsername( user.getUsername() );
        user1.setId( user.getId() );
        user1.setEmail( user.getEmail() );
        user1.setPassword( user.getPassword() );

        Set<Role> roles = new HashSet<>();
        Role defaultRole = roleRepository.findByName(ERole.DOCTOR)
                .orElseThrow(() -> new RuntimeException("Cannot find DOCTOR role"));
        roles.add(defaultRole);

        user1.setRoles(roles);

        return user1;
    }
}
