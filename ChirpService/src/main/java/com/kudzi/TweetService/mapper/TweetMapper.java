package com.kudzi.TweetService.mapper;

import com.kudzi.TweetService.dto.TweetDto;
import com.kudzi.TweetService.entity.Tweet;

import java.util.UUID;

public class TweetMapper {

    public Tweet mapToTweet(TweetDto tweetDto, Tweet tweet){
        tweet.setText(tweetDto.text);
        tweet.setUserId(tweetDto.user_id);
        tweet.setUsername(tweetDto.username);
        tweet.setFull_name(tweetDto.full_name);
        tweet.setMediaUrl(tweetDto.mediaUrl);
        tweet.setTweet_id(UUID.randomUUID().toString());
        tweet.setLikes(tweetDto.likes);
        tweet.setReplies(tweetDto.replies);
        tweet.setTimestamp(tweetDto.current);
        tweet.setReplied_id(tweet.replied_id);
        return tweet;
    }
}
