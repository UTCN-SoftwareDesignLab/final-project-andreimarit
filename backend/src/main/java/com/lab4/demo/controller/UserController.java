package com.lab4.demo.controller;

import com.lab4.demo.dto.UserListDTO;
import com.lab4.demo.dto.UserString;
import com.lab4.demo.model.ERole;
import com.lab4.demo.model.Role;
import com.lab4.demo.repo.RoleRepository;
import com.lab4.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

import static com.lab4.demo.UrlMapping.ID;
import static com.lab4.demo.UrlMapping.USER;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @GetMapping
    public List<UserString> allUsers() {
        return userService.allUsersForList();
    }

    @PostMapping
    public UserListDTO create(@RequestBody UserListDTO userListDTO){

        return userService.create(userListDTO);}

    @PutMapping(ID)
    public UserListDTO edit(@RequestBody UserString userString, @PathVariable Long id){

        HashSet<Role> roles = new HashSet<>();

            Role defaultRole = roleRepository.findByName(ERole.CLIENT)
                    .orElseThrow(() -> new RuntimeException("Cannot find CLIENT role"));
            roles.add(defaultRole);

        UserListDTO userListDTO = UserListDTO.builder().email(userString.getEmail()).username(userString.getUsername()).password(userString.getPassword()).wallet(userString.getWallet())
                .role(roles).build();
        return userService.update(id,userListDTO);}

    @DeleteMapping(ID)
    public void delete(@PathVariable Long id) {userService.delete(id);}


}
