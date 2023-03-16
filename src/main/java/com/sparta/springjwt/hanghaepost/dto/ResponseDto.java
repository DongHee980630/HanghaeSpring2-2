package com.sparta.springjwt.hanghaepost.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseDto {
    private int statusCode;
    private String message;


    public ResponseDto(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}
