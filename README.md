Chirp Backend

1.User-Service: This microservice is responsible for handling user related issue including creation,updating and deleting user accounts. It also has the tweetService as its feign client this is done to make sure that only the owner of the user account
             should be able to send particular tweets and delete their own tweets

             
2.ChirpService: This is the microservice that is handling all the tweets related logic from creation, deletion ,liking ,rechirps and bookmarks ,they are all done in the chirpservice


3.GatewayServer: I used Spring Cloud Gateway Server as my edge server , this is managing all the external request and making sure that the request are authenticated using the Keycloak OpenIDConnect security protocols. It will also be used for security
                 as it authenticates all the requests.



4.EurekaServer : This is my service discovery server that is faciliting the load balancing,and routing of my requests. The eureka server is being used by my Gateway Server and Feign Clients to make proper HTTP requests.



4.Config Server: This is responsible for managing the configurations of all the microservices the config server is using Github as its storage location and the configuration will be assigned to a microservice as soon as it registers with the config 
                  server

                  
6.Database : For the database l am using mongoDB as it offers easier management of database entities.

