package com.sparta.springjwt.hanghaepost.controller;

import com.sparta.springjwt.hanghaepost.dto.LoginRequestDto;
import com.sparta.springjwt.hanghaepost.dto.ResponseDto;
import com.sparta.springjwt.hanghaepost.dto.SignupRequestDto;
import com.sparta.springjwt.hanghaepost.entity.User;
import com.sparta.springjwt.hanghaepost.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private  final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto){
        userService.signup(signupRequestDto);
        return ResponseEntity.ok(new ResponseDto(200,"회원가입 성공"));
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return ResponseEntity.ok(new ResponseDto(200,"로그인 성공"));
    }

}
