package com.sparta.springjwt.hanghaepost.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private  Long id;

    // nullable: null 허용 여부
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)   //enum 이름을 db에 저장
    private UserRoleEnum roleEnum;



    public User(String username, String password, UserRoleEnum roleEnum) {
        this.username = username;
        this.password = password;
        this.roleEnum = roleEnum;
    }
}

