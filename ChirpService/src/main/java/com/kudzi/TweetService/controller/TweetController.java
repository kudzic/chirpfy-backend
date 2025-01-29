package com.kudzi.TweetService.controller;

import com.kudzi.TweetService.dto.TweetDto;
import com.kudzi.TweetService.entity.Tweet;
import com.kudzi.TweetService.mapper.TweetMapper;
import com.kudzi.TweetService.services.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tweet")
@CrossOrigin(origins = "http://localhost:5173")
public class TweetController {

    private final TweetService tweetService;

    @PostMapping("/addTweet")
    public ResponseEntity<String> addTweet(@RequestBody TweetDto tweetDto){
        TweetMapper mapper=new TweetMapper();
        Tweet actual= mapper.mapToTweet(tweetDto,new Tweet());
        tweetService.addTweet(actual);
        return ResponseEntity.status(201).body("Tweet has been added");
    }

    @GetMapping("/tweetsid")
    public List<Tweet> getAllTweetsByID(@RequestParam String id){
        return tweetService.getTweetsById(id);
    }
    @GetMapping("/tweets")
    public List<Tweet> getAllTweets(){
        return tweetService.getAllTweets();
    }

    @GetMapping("/tweet")
    public Tweet getTweet(@RequestBody String tweetid){
      return tweetService.getTweet(tweetid);
    }

    @DeleteMapping("/delete")
    public String deleteTweet(@RequestParam String tweetid) throws Exception {
        tweetService.deleteTweet(tweetid);
        return "Tweet has been deleted";

    }

    @GetMapping("/usertweets")
    public List<Tweet> getAllTweetByUser(@RequestParam String username){
        return tweetService.getTweetByUser(username);
    }

    @GetMapping ("/like")
    public Long likeTweet(@RequestParam  String tweetid, @RequestParam String userId){
       return tweetService.likeTweet(tweetid,userId);
    }

    @GetMapping ("/unlike")
    public Long unlikeTweet(@RequestParam  String tweetid,@RequestParam String userId){
        return tweetService.unlikeTweet(tweetid,userId);
    }


    @PostMapping("/reply")
    public List<Tweet> replyTweet(@RequestParam String tweetid,@RequestBody TweetDto Tweet){
      return tweetService.tweetReplies(tweetid,Tweet);
    }


}
