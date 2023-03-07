package com.sparta.springjwt.hanghaepost.controller;

import com.sparta.springjwt.hanghaepost.dto.PostRequestsDto;
import com.sparta.springjwt.hanghaepost.dto.PostResponseDto;
import com.sparta.springjwt.hanghaepost.entity.Post;
import com.sparta.springjwt.hanghaepost.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/api/posts") public PostResponseDto createPost(@RequestBody PostRequestsDto requestsDto, HttpServletRequest request){
        return postService.createPost(requestsDto, request);
    }
    @GetMapping("/api/posts")
    public List<Post> getPosts(){
        return postService.getPosts();
    }

    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestsDto requestDto, HttpServletRequest request){
        return postService.update(id, requestDto, request);
    }

    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id, @RequestBody PostRequestsDto requestDto, HttpServletRequest request){
        return postService.deletePost(id, requestDto, request);
    }
}
