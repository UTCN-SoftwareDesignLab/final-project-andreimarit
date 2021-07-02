package com.lab4.demo.service.mapper;

import com.lab4.demo.dto.UserListDTO;
import com.lab4.demo.dto.UserMinimalDTO;
import com.lab4.demo.dto.UserString;
import com.lab4.demo.model.User;
import org.mapstruct.*;

public interface UserMapper {

    @Mappings({
            @Mapping(target = "username", source = "user.username")
    })
    UserMinimalDTO userMinimalFromUser(User user);

    @Mappings({
            @Mapping(target = "username", source = "user.username")
    })
    UserListDTO userListDtoFromUser(User user);

    UserString userStringFromUser(User user);

    @AfterMapping
    default void populateRoles(User user, @MappingTarget UserListDTO userListDTO) {

        userListDTO.setRole(user.getRoles());
        //userListDTO.setRole(user.getRoles().stream().findFirst().get().getName());
    }
    //.stream().map(role -> role.getName().username()).collect(Collectors.toSet())

    @Mappings({
            @Mapping(target = "username", source = "user.username")
    })
    UserListDTO toDTO(User user);

    @Mappings({
            @Mapping(target = "username", source = "username")
    })
    User fromDTO(UserListDTO user);
}
