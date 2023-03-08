package com.sparta.springjwt.hanghaepost.service;

import com.sparta.springjwt.hanghaepost.dto.PostRequestsDto;
import com.sparta.springjwt.hanghaepost.dto.PostResponseDto;
import com.sparta.springjwt.hanghaepost.entity.Post;
import com.sparta.springjwt.hanghaepost.entity.User;
import com.sparta.springjwt.hanghaepost.jwt.JwtUtil;
import com.sparta.springjwt.hanghaepost.repository.PostRepository;
import com.sparta.springjwt.hanghaepost.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public PostResponseDto createPost(PostRequestsDto requestsDto, HttpServletRequest request){
        //  Token 가져오기
        String token = jwtUtil.resolveToken(request);
        //payload : 클레임을 포함한다. 즉, 클라이언트에 대한 정보가 담겨있음
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            //요청받은 DTO로 db에 저장할 객체 만들기
            Post post = postRepository.saveAndFlush(new Post(requestsDto, user.getId()));

            return new PostResponseDto(post);
        }else {
            throw new IllegalArgumentException("토큰없음");
        }
    }
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public PostResponseDto update(Long id, PostRequestsDto requestsDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }


            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Post post = postRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("해당 게시물은 존재하지 않습니다.")
            );
            post.update(requestsDto);
            return new PostResponseDto(post);
        }else {
            throw new IllegalArgumentException("토큰없음");
        }
    }

    @Transactional
    public Long deletePost(Long id, PostRequestsDto requestsDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Post post = postRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("해당 게시물은 존재하지 않습니다.")
            );
            postRepository.deleteById(id);
            return id;
        }else {
            throw new IllegalArgumentException("토큰없음");
        }
    }

    public PostResponseDto getpost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("게시글이 없습니다." + id));
        return new PostResponseDto(post);
    }
}
