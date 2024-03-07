package com.example.TwitterGatewayServer.Beans;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {


    @Bean
    public Keycloak keycloak(){
        return KeycloakBuilder.builder()
                .realm("Twitter-Clone")
                .clientId("admin-cli")
                .serverUrl("http://localhost:7080")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientSecret("Bz8Ja3by2c1wNUNPCPEeWXtuT43wwtvj")
                .build();
    }
}
