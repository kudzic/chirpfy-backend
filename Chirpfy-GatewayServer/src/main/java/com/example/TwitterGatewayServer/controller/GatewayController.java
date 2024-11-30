package com.example.TwitterGatewayServer.controller;



import com.example.TwitterGatewayServer.dto.UserRegistrationRecord;
import com.example.TwitterGatewayServer.service.KeyCloakUserServiceImpl;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/principal")
@Path("/api/principal")
@CrossOrigin()
public class GatewayController {

 private KeyCloakUserServiceImpl keyCloakUserService;



    public GatewayController(KeyCloakUserServiceImpl keyCloakUserService) {
        this.keyCloakUserService = keyCloakUserService;

    }

    @PostMapping("/addUser")
    @POST
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
