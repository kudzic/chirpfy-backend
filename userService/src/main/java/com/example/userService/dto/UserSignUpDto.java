package com.example.userService.dto;

import com.example.userService.entity.User;
import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpDto {
    private String firstName;
    private String lastName;
    private String password;
    private String  email;

}
