package com.messageattempt.springbootmessage.service;

import com.messageattempt.springbootmessage.entity.User;

import java.util.List;

public interface UserService {

    // get all users
    List<User> getAllUsers();

    // get user by ID
    User getUserById(Long id);

    // create new user
    User addNewUser(User user);

}
