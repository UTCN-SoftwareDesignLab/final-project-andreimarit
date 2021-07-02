package com.lab4.demo.controller;

import com.lab4.demo.dto.UserListDTO;
import com.lab4.demo.dto.UserString;
import com.lab4.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.lab4.demo.UrlMapping.ID;
import static com.lab4.demo.UrlMapping.USER;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserString> allUsers() {
        return userService.allUsersForList();
    }

    @PostMapping
    public UserListDTO create(@RequestBody UserListDTO userListDTO){
        System.out.println(userListDTO);
        System.out.println(userListDTO.getEmail());
        System.out.println(userListDTO.getUsername());
        System.out.println(userListDTO.getPassword());
        return userService.create(userListDTO);}

    @PutMapping(ID)
    public UserListDTO edit(@PathVariable Long id, @RequestBody UserListDTO userListDTO){return userService.update(id,userListDTO);}

    @DeleteMapping(ID)
    public void delete(@PathVariable Long id) {userService.delete(id);}


}
