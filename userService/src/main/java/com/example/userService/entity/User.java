package com.example.userService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    private String userId;
    private String first_name;
    private String username;
    private String lastName;
    private String email;
    private LocalDateTime accountCreatedOn;
    private List<User> followers;
    private List<User> follows;
    private Set<Integer> likes;
    private Set<Integer> bookmarks;
    private Set<Integer> retweets;




}
