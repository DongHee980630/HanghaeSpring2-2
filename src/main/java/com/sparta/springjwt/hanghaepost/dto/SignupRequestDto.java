package com.sparta.springjwt.hanghaepost.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SignupRequestDto {
    private String username;
    private String password;
}
