package com.example.soapapis.endpoints;

import com.danielthelux.soap_api.users.*;
import com.example.soapapis.repositories.UserRepository;
import com.example.soapapis.servicesImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.annotation.PostConstruct;
import java.util.*;

@Endpoint
public class UsersEndpoint {

    public static Map<Integer,User> users = new HashMap<>();

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initialize(){
        User user1 = new User();
        user1.setUserId(1);
        user1.setFullName("daniel");
        user1.setEmail("daniel@gmail.com");
        user1.setClassName("class a");

        User user2 = new User();
        user2.setUserId(2);
        user2.setFullName("mugisha");
        user2.setEmail("mugisha@gmail.com");
        user2.setClassName("class r");

        users.put(user1.getUserId(),user1);
        users.put(user2.getUserId(),user1);
    }

    @PayloadRoot(
            namespace = "http://danielthelux.com/soap-api/users",
            localPart = "getUserRequest"
    )
    @ResponsePayload
    public GetUserResponse getUserResponse(@RequestPayload GetUserRequest getUserRequest){

        /**
         * Logic implementation
         */
        com.example.soapapis.beans.User userFound = userRepository.findByFullName(getUserRequest.getName());
        System.out.println(" User: "+userFound);

        /**
         * Forwarding user response object
         */

        GetUserResponse response = new GetUserResponse();

        User userToSend = new User();
        userToSend.setUserId(Integer.valueOf(userFound.getUserId().toString()));
        userToSend.setFullName(userFound.getFullName());
        userToSend.setEmail(userFound.getEmail());
        userToSend.setClassName(userFound.getClassName());

        response.setUser(userToSend);

        return response;
    }

    @PayloadRoot(
            namespace = "http://danielthelux.com/soap-api/users",
            localPart = "getAllUsersRequest"
    )
    @ResponsePayload
    public GetAllUsersResponse getAllUsersResponse(){

        GetAllUsersResponse response = new GetAllUsersResponse();

        Iterator iterator = users.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry user = (Map.Entry) iterator.next();

            response.getUser().add((User) user.getValue());
        }

        return response;
    }


    @PayloadRoot(
            namespace = "http://danielthelux.com/soap-api/users",
            localPart = "deleteUserRequest"
    )
    @ResponsePayload
    public DeleteUserResponse deleteUserResponse(@RequestPayload DeleteUserRequest request){

        DeleteUserResponse response = new DeleteUserResponse();

        User userRemoved = users.remove(request.getUserId());

        response.setUser(userRemoved);

        return response;
    }


}
