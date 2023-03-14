package com.sparta.springjwt.hanghaepost.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@RequiredArgsConstructor
public class SignupRequestDto {
    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{4,10}", message = "유효하지 않은 아이디 형식")
    private final String username;

    @NotBlank
    @Pattern(regexp = "^[`~!@#$%^&*)(}{\\]\\[,./?><;':\\-_+=\\w]{8,15}$", message = "유효하지 않은 비밀번호 형식")
    private final String password;

    private final boolean admin = false;

    private final String adminToken = "";
}
