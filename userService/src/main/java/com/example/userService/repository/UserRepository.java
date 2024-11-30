package com.example.userService.repository;

import com.example.userService.entity.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    boolean existsByEmail(String email);
    User findUserByEmail(String email);
    User findUserByUserId(String userId);
}
