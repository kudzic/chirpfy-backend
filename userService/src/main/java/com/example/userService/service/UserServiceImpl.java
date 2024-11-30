package com.example.userService.service;

import com.example.userService.clients.TweetFeignClient;
import com.example.userService.dto.TweetDto;
import com.example.userService.dto.UserDto;
import com.example.userService.dto.UserSignUpDto;
import com.example.userService.entity.User;
import com.example.userService.mapper.UserMapper;
import com.example.userService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    public final UserRepository userRepository;
    public final TweetFeignClient tweetFeignClient;

    @Override
    public void createAccount(UserRepresentation dto) throws Exception {
        UserMapper userMapper=new UserMapper();
        User user=userMapper.userSignUpDtoToUser(dto,new User());

        if(!userRepository.existsByEmail(user.getEmail())){
            userRepository.save(user);
        }
        else{
            throw new Exception("Account with same email already exist");
        }
    }

    @Override
    public void login(UserDto dto) {

    }


    public void addTweet(TweetDto tweetDto,String email){
        if(userRepository.existsByEmail(email)) {
            User userId = userRepository.findUserByEmail(email);
            if (userRepository.existsById(userId.getUserId())) {
                User user = userRepository.findUserByUserId(userId.getUserId());
                tweetDto.user_id = user.getUserId();
                tweetDto.full_name = user.getFirst_name() + " " + user.getLastName();
                tweetDto.username = user.getUsername();
                tweetFeignClient.addTweet(tweetDto);
            }
        }



    }

    @Override
    public void follow() {

    }

    @Override
    public String resetPassword(String password) {
        return null;
    }
}
