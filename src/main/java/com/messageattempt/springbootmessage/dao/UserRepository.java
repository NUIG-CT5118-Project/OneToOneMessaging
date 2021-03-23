package com.messageattempt.springbootmessage.dao;

import com.messageattempt.springbootmessage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

    // find user by unique employee number
    Optional<User> findByEmployeeNumber(@Param("employeeNumber") String employeeNumber);

}
