package com.example.TwitterGatewayServer.service;

import com.example.TwitterGatewayServer.dto.UserRegistrationRecord;
import org.keycloak.representations.idm.UserConsentRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakUserService {

    UserRegistrationRecord createUser(UserRegistrationRecord userRegistrationRecord) throws Exception;
    UserRepresentation getUserById(String userId);

    void deleteUser(String userId);
}
