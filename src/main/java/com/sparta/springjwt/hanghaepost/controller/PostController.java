package com.sparta.springjwt.hanghaepost.controller;

import com.sparta.springjwt.hanghaepost.dto.PostRequestsDto;
import com.sparta.springjwt.hanghaepost.dto.PostResponseDto;
import com.sparta.springjwt.hanghaepost.dto.ResponseDto;
import com.sparta.springjwt.hanghaepost.entity.Post;
import com.sparta.springjwt.hanghaepost.security.UserDetailsImpl;
import com.sparta.springjwt.hanghaepost.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/api/posts") public PostResponseDto createPost(@RequestBody PostRequestsDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.createPost(requestDto, userDetails.getUser());
    }
    @GetMapping("/api/posts")
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/api/post/{id}")
    public ResponseEntity<PostResponseDto> getpost(@PathVariable Long id){
        PostResponseDto postResponseDto = postService.getpost(id);
        return ResponseEntity.status(HttpStatus.OK).body(postResponseDto);
    }

    @PutMapping("/api/posts/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long id, @RequestBody PostRequestsDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        PostResponseDto postResponseDto = postService.update(id, requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(postResponseDto);
    }

    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<ResponseDto> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.deletePost(id, userDetails.getUser());
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "게시글 삭제 성공"));
    }
}
