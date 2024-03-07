package com.example.userService.service;


import com.example.userService.dto.UserDto;
import com.example.userService.dto.UserSignUpDto;
import org.keycloak.representations.idm.UserRepresentation;

public interface UserService {
     public void createAccount(UserRepresentation dto)throws Exception;
     public void login(UserDto dto);
     public void follow();
     public String resetPassword(String password);



}
