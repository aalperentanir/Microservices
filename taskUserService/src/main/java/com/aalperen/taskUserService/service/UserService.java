package com.aalperen.taskUserService.service;
import com.aalperen.taskUserService.entity.User;

import java.util.List;

public interface UserService {

    User getUserProfile(String jwt) throws Exception;

    List<User> getUsers();
}