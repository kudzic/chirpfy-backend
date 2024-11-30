package com.example.userService.controller;

import com.example.userService.clients.TweetFeignClient;
import com.example.userService.dto.TweetDto;
import com.example.userService.dto.UserDto;
import com.example.userService.dto.UserSignUpDto;
import com.example.userService.entity.User;
import com.example.userService.repository.UserRepository;
import com.example.userService.service.UserService;
import com.example.userService.service.UserServiceImpl;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    public final UserServiceImpl userService;



    @PostMapping("/addTweet")
    public ResponseEntity<String> addTweet(@RequestBody TweetDto tweetDto,@RequestParam String email){
        userService.addTweet(tweetDto,email);
       return ResponseEntity.ok("Tweet Added");
    }


    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserRepresentation dto) throws Exception {
        userService.createAccount(dto);
        return ResponseEntity.status(HttpStatus.OK).body("Account has been created");

    }

}
