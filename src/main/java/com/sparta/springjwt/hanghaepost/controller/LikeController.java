package com.sparta.springjwt.hanghaepost.controller;

import com.sparta.springjwt.hanghaepost.dto.ResponseDto;
import com.sparta.springjwt.hanghaepost.security.UserDetailsImpl;
import com.sparta.springjwt.hanghaepost.service.LikeNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class LikeController {
    private final LikeNumberService likeService;
    @PostMapping("/{postId}/like")
    public ResponseDto postAddLike(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.postLike(postId, userDetails.getUser());
    }

    @PostMapping("/{postId}/{commentId}/like")
    public ResponseDto commentAddLike(@PathVariable Long postId,@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.commentLike(postId,commentId,userDetails.getUser());
    }

}
