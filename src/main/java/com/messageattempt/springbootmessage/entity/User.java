package com.messageattempt.springbootmessage.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "employee_number", nullable = false, unique = true)
    private String employeeNumber;

    public User(String userName, String employeeNumber) {
        this.userName = userName;
        this.employeeNumber = employeeNumber;
    }

}
