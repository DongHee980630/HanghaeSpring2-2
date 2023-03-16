package com.sparta.springjwt.hanghaepost.controller;

import com.sparta.springjwt.hanghaepost.dto.CommentRequestDto;
import com.sparta.springjwt.hanghaepost.dto.CommentResponseDto;
import com.sparta.springjwt.hanghaepost.dto.ResponseDto;
import com.sparta.springjwt.hanghaepost.security.UserDetailsImpl;
import com.sparta.springjwt.hanghaepost.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}/comment")
    public CommentResponseDto createComment(
            @PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(postId, commentRequestDto, userDetails.getUser());
    }

    @PutMapping("/{postId}/comment/{commentId}")
    public CommentResponseDto updateComment(
            @PathVariable Long postId, @PathVariable Long commentId,@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return commentService.updateComment(postId, commentId, commentRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<ResponseDto> deletePost( @PathVariable Long postId,@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        commentService.deletePost(postId,commentId, userDetails.getUser());
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "게시글 삭제 성공"));
    }
}
