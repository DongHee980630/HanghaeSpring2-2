package com.sparta.springjwt.hanghaepost.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@ToString
public class SignupRequestDto {
    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{4,10}")
    private String username;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,15}")
    private String password;
}
