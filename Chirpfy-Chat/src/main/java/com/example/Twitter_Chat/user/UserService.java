package com.example.Twitter_Chat.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(Users user){
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }

    public void disconnet(Users user){
        var storedUser=userRepository.findByNickname(user.getNickname()).orElse(null);
        if(storedUser!=null) {
            userRepository.delete(storedUser);
            user.setStatus(Status.OFFLINE);
            userRepository.save(user);
        }
    }

    public List<Users> findConnectedUser(){
        return userRepository.findAllByStatus(Status.ONLINE);
    }
}
