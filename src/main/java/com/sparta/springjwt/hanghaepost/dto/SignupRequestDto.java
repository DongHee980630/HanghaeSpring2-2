package com.sparta.springjwt.hanghaepost.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@RequiredArgsConstructor
public class SignupRequestDto {
    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{4,10}")
    private final String username;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,15}")
    private final String password;

    private final boolean admin = false;

    private final String adminToken = "";
}
