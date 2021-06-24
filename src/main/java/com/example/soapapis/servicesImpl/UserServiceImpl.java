package com.example.soapapis.servicesImpl;

import com.example.soapapis.beans.User;
import com.example.soapapis.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl{

    private UserRepository userRepository;

    public User getUserByName(String username){
        return userRepository.findByFullName(username);
    }

}
