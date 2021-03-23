package com.messageattempt.springbootmessage.service;

import com.messageattempt.springbootmessage.dao.UserRepository;
import com.messageattempt.springbootmessage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // get all users
    @Override
    public List<User> getAllUsers() {

        return this.userRepository.findAll();

    }

    // get user by ID
    public User getUserById(Long id) {

        // find user by ID
        Optional<User> userOptional = this.userRepository.findById(id);

        // if found return user or else throw exception if not found
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new IllegalStateException("User with id" + id + " does not exist.");
        }

    }

    // create new user
    @Override
    public User addNewUser(User user) {

        // find user by employee number
        Optional<User> userOptional = userRepository.findByEmployeeNumber(user.getEmployeeNumber());

        // if user with employee number already exists throw exception
        if (userOptional.isPresent()) {
            throw new IllegalStateException("User with number " + user.getEmployeeNumber() + " already exists.");
        }

        return userRepository.save(user);

    }
}
