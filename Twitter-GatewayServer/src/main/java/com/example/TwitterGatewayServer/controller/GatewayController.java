package com.example.TwitterGatewayServer.controller;



import com.example.TwitterGatewayServer.dto.UserRegistrationRecord;
import com.example.TwitterGatewayServer.service.KeyCloakUserServiceImpl;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/principal")
public class GatewayController {

 private KeyCloakUserServiceImpl keyCloakUserService;



    public GatewayController(KeyCloakUserServiceImpl keyCloakUserService) {
        this.keyCloakUserService = keyCloakUserService;

    }

    @PostMapping("/addUser")
    public UserRegistrationRecord createUser(@RequestBody UserRegistrationRecord user) throws Exception{

      return  keyCloakUserService.createUser(user);
    }

    @GetMapping("/get")
    public UserRepresentation getUser(@RequestParam String id){
        return keyCloakUserService.getUserById(id);
    }

    @PutMapping("/delete")
    public void deleteUser(@RequestParam  String id){
        keyCloakUserService.deleteUser(id);
    }
}
