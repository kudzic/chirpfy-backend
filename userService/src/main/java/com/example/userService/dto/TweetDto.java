package com.example.userService.dto;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class TweetDto {
    public String text;

    public String username;
    public String full_name;

    public String user_id;
    public String mediaUrl;
    public Long likes=0L;
    public LocalDateTime current=LocalDateTime.now();
    public String replied_to=new String();


}
