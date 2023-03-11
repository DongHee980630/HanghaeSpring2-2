package com.sparta.springjwt.hanghaepost.controller;

import com.sparta.springjwt.hanghaepost.dto.CommentRequestDto;
import com.sparta.springjwt.hanghaepost.dto.CommentResponseDto;
import com.sparta.springjwt.hanghaepost.dto.ResponseDto;
import com.sparta.springjwt.hanghaepost.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}/comment")
    public CommentResponseDto createComment(
            @PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        return commentService.createComment(postId, commentRequestDto, request);
    }

    @PutMapping("/{postId}/comment/{commentId}")
    public CommentResponseDto updateComment(
            @PathVariable Long postId, @PathVariable Long commentId,@RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request
    ) {
        return commentService.updateComment(postId, commentId, commentRequestDto, request);
    }

    @DeleteMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<ResponseDto> deletePost( @PathVariable Long postId,@PathVariable Long commentId, HttpServletRequest request){
        commentService.deletePost(postId,commentId, request);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "게시글 삭제 성공"));
    }
}
