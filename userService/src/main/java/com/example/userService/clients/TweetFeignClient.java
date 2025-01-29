package com.example.userService.clients;

import com.example.userService.dto.TweetDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="chirpservice")
public interface TweetFeignClient {

    @PostMapping("/api/tweet/addTweet")
    public String addTweet(@RequestBody TweetDto tweetDto);
}
