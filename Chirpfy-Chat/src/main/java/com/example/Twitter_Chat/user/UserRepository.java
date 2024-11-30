package com.example.Twitter_Chat.user;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<Users,String> {

    Optional<Users> findByNickname(String nickname);
    List<Users> findAllByStatus(Status status);
}
