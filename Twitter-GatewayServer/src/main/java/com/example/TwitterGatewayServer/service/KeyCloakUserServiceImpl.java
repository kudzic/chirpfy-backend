package com.example.TwitterGatewayServer.service;

import com.example.TwitterGatewayServer.dto.UserRegistrationRecord;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class KeyCloakUserServiceImpl implements KeycloakUserService {
     private Keycloak keyCloak;


 

    @Override
    public UserRegistrationRecord createUser(UserRegistrationRecord userRegistrationRecord) throws Exception {
        UserRepresentation user=new UserRepresentation();


        user.setEnabled(true);
        user.setUsername(userRegistrationRecord.username());
        user.setEmail(userRegistrationRecord.email());
        user.setFirstName(userRegistrationRecord.firstName());
        user.setLastName(userRegistrationRecord.lastName());
        user.setEmailVerified(true);

        CredentialRepresentation credentialRepresentation=new CredentialRepresentation();
        credentialRepresentation.setValue(userRegistrationRecord.password());
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        List<CredentialRepresentation> list=new ArrayList<>();
        list.add(credentialRepresentation);
        user.setCredentials(list);
        UsersResource users = getUsersResource();

        Response response=users.create(user);


        List<UserRepresentation> representations=users.searchByUsername(userRegistrationRecord.username(),true);

        if(response.getStatus()==201){
            user.setId(representations.get(0).getId());
        }


        if(!representations.isEmpty()){
            System.out.println(representations.get(0).getId());
        }
        if(Objects.equals(201,response.getStatus()))
        {
            WebClient webClient=WebClient.create("http://localhost:8083/api/user");
            webClient.post()
                    .uri("/signup")
                    .bodyValue(user)
                    .retrieve()
                    .bodyToMono(String.class)
                    .subscribe(responseBody ->
                    {
                        System.out.println("Response "+ responseBody);
                    });

            return userRegistrationRecord;
        }

        return null;
    }

    private UsersResource getUsersResource() {
        RealmResource realmResource = keyCloak.realm("Twitter-Clone");
        UsersResource users = realmResource.users();
        return users;
    }

    @Override
    public UserRepresentation getUserById(String userId) {

        return getUsersResource().get(userId).toRepresentation();

    }


    @Override
    public void deleteUser(String userId) {
        getUsersResource().delete(userId);
    }
}
