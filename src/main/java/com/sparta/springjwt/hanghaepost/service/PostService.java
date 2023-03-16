package com.sparta.springjwt.hanghaepost.service;

import com.sparta.springjwt.hanghaepost.dto.PostRequestsDto;
import com.sparta.springjwt.hanghaepost.dto.PostResponseDto;
import com.sparta.springjwt.hanghaepost.entity.Post;
import com.sparta.springjwt.hanghaepost.entity.User;
import com.sparta.springjwt.hanghaepost.entity.UserRoleEnum;
import com.sparta.springjwt.hanghaepost.jwt.JwtUtil;
import com.sparta.springjwt.hanghaepost.repository.PostRepository;
import com.sparta.springjwt.hanghaepost.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    @Transactional
        public PostResponseDto createPost(PostRequestsDto requestsDto, User user){
            //요청받은 DTO로 db에 저장할 객체 만들기
            Post post = postRepository.saveAndFlush(new Post(requestsDto, user));
            return new PostResponseDto(post);
        }
    public List<PostResponseDto> getPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        return posts.stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getpost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("게시글이 없습니다." + id));
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto update(Long id, PostRequestsDto requestsDto, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물은 존재하지 않습니다.")
        );
        if (user.getRoleEnum() == UserRoleEnum.ADMIN || user.getId().equals(post.getUser().getId())) {
            post.update(requestsDto);
            return new PostResponseDto(post);
        } else {
            throw new IllegalArgumentException("수정권한 없음");
        }
    }

    @Transactional
    public Long deletePost(Long id, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물은 존재하지 않습니다.")
        );
        if (user.getRoleEnum() == UserRoleEnum.ADMIN || user.getId().equals(post.getUser().getId())) {
            postRepository.deleteById(id);
            return id;
        } else {
            throw new IllegalArgumentException("삭제 권한 없음");
        }
    }
    }

