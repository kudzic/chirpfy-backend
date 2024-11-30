package com.example.Twitter_Chat.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;


    @MessageMapping("/user.add")
    @SendTo("/user/public")
    public Users addUser(@Payload Users user){
        userService.saveUser(user);
        return user;
    }

    @PostMapping("/addUser")
    public Users addUsers(@RequestBody Users user){
        userService.saveUser(user);
        return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/topic")
    public Users disconnect(@Payload Users user){
        userService.disconnet(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> findConnectedUsers(){
        return ResponseEntity.ok(userService.findConnectedUser());
    }





}
