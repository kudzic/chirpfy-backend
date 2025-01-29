package com.example.userService.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class User {
    @Id
    private String userId;
    private String firstName;
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
