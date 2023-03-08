package com.sparta.springjwt.hanghaepost.controller;

import com.sparta.springjwt.hanghaepost.dto.PostRequestsDto;
import com.sparta.springjwt.hanghaepost.dto.PostResponseDto;
import com.sparta.springjwt.hanghaepost.dto.ResponseDto;
import com.sparta.springjwt.hanghaepost.entity.Post;
import com.sparta.springjwt.hanghaepost.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/api/post/{id}")
    public ResponseEntity<PostResponseDto> getpost(@PathVariable Long id){
        PostResponseDto postResponseDto = postService.getpost(id);
        return ResponseEntity.status(HttpStatus.OK).body(postResponseDto);
    }

    @PutMapping("/api/posts/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long id, @RequestBody PostRequestsDto requestDto, HttpServletRequest request){
        PostResponseDto postResponseDto = postService.update(id, requestDto, request);
        return ResponseEntity.status(HttpStatus.OK).body(postResponseDto);
    }

    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<ResponseDto> deletePost(@PathVariable Long id, @RequestBody PostRequestsDto requestDto, HttpServletRequest request){
        postService.deletePost(id, requestDto, request);
        return ResponseEntity.ok(new ResponseDto(200,"게시글 삭제 성공"));
    }
}
