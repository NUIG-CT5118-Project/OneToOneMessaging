package com.messageattempt.springbootmessage.controller;

import com.messageattempt.springbootmessage.entity.User;
import com.messageattempt.springbootmessage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    // to get all users
    @GetMapping("/users")
    public List<User> getAllUsers() { return userService.getAllUsers(); }

    // to get a user by ID
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) { return userService.getUserById(id); }

    // to add user
    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) { return userService.addNewUser(user); }

}
