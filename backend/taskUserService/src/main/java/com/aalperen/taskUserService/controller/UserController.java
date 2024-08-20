package com.aalperen.taskUserService.controller;


import java.util.List;

import com.aalperen.taskUserService.entity.User;
import com.aalperen.taskUserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public ResponseEntity<List<User>> getUserProfile() throws Exception{
        List<User> users = userService.getUsers();

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/users/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.getUserProfile(jwt);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }



}
