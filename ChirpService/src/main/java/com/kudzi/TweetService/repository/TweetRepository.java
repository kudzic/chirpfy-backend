package com.kudzi.TweetService.repository;

import com.kudzi.TweetService.entity.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;
@Component
public interface TweetRepository extends MongoRepository<Tweet,String> {
    List<Tweet> findAllByUserId(String userId);

    List<Tweet> findAllByUsername(String username);
}
