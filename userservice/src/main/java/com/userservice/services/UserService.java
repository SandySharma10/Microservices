package com.userservice.services;

import com.userservice.entityes.User;

import java.util.List;

public interface UserService {
    //we can write business logic with method
     User saveUser(User user);

     List<User> getAllUser();

     User getUser(String userId);

     //write logic of update and delete



}
