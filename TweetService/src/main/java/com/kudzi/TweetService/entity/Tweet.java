package com.kudzi.TweetService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tweet {
    @Id
    public String tweet_id;
    public String text;
    public String mediaUrl;
    public String userId;
    public String username;
    public String full_name;
    public Long  likes;
    public ArrayList<Tweet> replies;
    public LocalDateTime timestamp;
    public String replied_id;

}
