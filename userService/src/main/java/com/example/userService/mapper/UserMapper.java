package com.example.userService.mapper;

import com.example.userService.dto.UserDto;
import com.example.userService.dto.UserSignUpDto;
import com.example.userService.entity.User;
import org.keycloak.representations.idm.UserRepresentation;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class UserMapper {

    public User userSignUpDtoToUser(UserRepresentation dto, User user){
        user.setUserId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setFirst_name(dto.getFirstName());
        user.setUsername(dto.getUsername());
        user.setLastName(dto.getLastName());
        user.setAccountCreatedOn(LocalDateTime.now());
        user.setFollowers(new LinkedList<User>());
        user.setFollows(new LinkedList<User>());
        return user;
    }
}
