package com.example.Twitter_Chat.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Document(collection = "users")

public class Users {
    @Id
    private final String nickname;
    private final String fullname;
    private Status status;


    @JsonCreator
    public Users(@JsonProperty("nickname")String nickname,@JsonProperty("fullname")String fullname,@JsonProperty("status")Status status){
        this.nickname=nickname;
        this.fullname=fullname;
        this.status=status;
    }

}
