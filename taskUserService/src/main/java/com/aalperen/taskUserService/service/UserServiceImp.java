package com.aalperen.taskUserService.service;


import java.util.List;

import com.aalperen.taskUserService.config.JwtProvider;
import com.aalperen.taskUserService.entity.User;
import com.aalperen.taskUserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserProfile(String jwt) throws Exception{
        String email = JwtProvider.getEmailFromJwt(jwt);

        User user = userRepository.findByEmail(email);

        if(user == null) {
            throw new Exception("User not found");
        }

        return user;
    }

    @Override
    public List<User> getUsers() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }

}