package com.lab4.demo.service;

import com.lab4.demo.dto.UserListDTO;
import com.lab4.demo.dto.UserMinimalDTO;
import com.lab4.demo.dto.UserString;
import com.lab4.demo.model.ERole;
import com.lab4.demo.model.Role;
import com.lab4.demo.repo.RoleRepository;
import com.lab4.demo.repo.UserRepository;
import com.lab4.demo.service.mapper.UserMapper;
import com.lab4.demo.model.User;
import com.lab4.demo.websocket.WsServerEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    public List<UserMinimalDTO> allUsersMinimal() {
        return userRepository.findAll()
                .stream().map(userMapper::userMinimalFromUser)
                .collect(toList());
    }

    public List<UserString> allUsersForList() {
        return userRepository.findAll()
                .stream().map(userMapper::userStringFromUser)
                .collect(toList());
    }

    public void delete(Long id){

        WsServerEndpoint.sendAlert("Farewell Doctor " + userRepository.findById(id).get().getUsername() + "!");
        userRepository.deleteById(id);
    }

    public UserListDTO create(UserListDTO userListDTO){

        Set<Role> roles = new HashSet<>();
        Role defaultRole = roleRepository.findByName(ERole.DOCTOR)
                .orElseThrow(() -> new RuntimeException("Cannot find DOCTOR role"));
        roles.add(defaultRole);
        WsServerEndpoint.sendAlert("A new Doctor joins our clinic! Welcome, " + userListDTO.getUsername() + "!");
        userListDTO.setRole(roles);
        String password = encoder.encode(userListDTO.getPassword());
        userListDTO.setPassword(password);
        return userMapper.toDTO(userRepository.save(userMapper.fromDTO(userListDTO)));
    }

    public UserListDTO update(Long id, UserListDTO userListDTO){
        User user = findById(id);
        user.setEmail(userListDTO.getEmail());
        user.setUsername(userListDTO.getUsername());
        user.setPassword(encoder.encode(userListDTO.getPassword()));
        user.setRoles(userListDTO.getRole());
        return userMapper.toDTO(userRepository.save(user));

    }


    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }
}
