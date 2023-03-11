package com.sparta.springjwt.hanghaepost.service;

import com.sparta.springjwt.hanghaepost.dto.CommentRequestDto;
import com.sparta.springjwt.hanghaepost.dto.CommentResponseDto;
import com.sparta.springjwt.hanghaepost.entity.Comment;
import com.sparta.springjwt.hanghaepost.entity.Post;
import com.sparta.springjwt.hanghaepost.entity.User;
import com.sparta.springjwt.hanghaepost.entity.UserRoleEnum;
import com.sparta.springjwt.hanghaepost.jwt.JwtUtil;
import com.sparta.springjwt.hanghaepost.repository.CommentRepository;
import com.sparta.springjwt.hanghaepost.repository.PostRepository;
import com.sparta.springjwt.hanghaepost.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;

    private User findUserByToken(HttpServletRequest request){
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
            return user;
        }else {
            throw new IllegalArgumentException("토큰없음");
        }
    }
    @Transactional
    public CommentResponseDto createComment(Long postId, CommentRequestDto commentRequestDto, HttpServletRequest request) {
        User user = findUserByToken(request);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글 ID가 유효하지 않습니다."));
        Comment comment = commentRepository.save(new Comment(commentRequestDto, user, post));
        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long postId, Long commentId, CommentRequestDto commentRequestDto, HttpServletRequest request) {
        User user = findUserByToken(request);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글 ID가 유효하지 않습니다."));
        Comment comment = commentRepository.findByIdAndUserId(commentId, user.getId()).orElseThrow(
                () -> new NullPointerException("해당 댓글은 존재하지 않습니다.")
        );
        if (user.getRoleEnum() == UserRoleEnum.ADMIN || user.getId().equals(post.getUser().getId())){
            comment.update(commentRequestDto);
            return new CommentResponseDto(comment);
        }else {
            throw new IllegalArgumentException("수정권한 없음");
        }
    }

    public Long deletePost(Long postId, Long commentId, HttpServletRequest request) {
        User user = findUserByToken(request);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글 ID가 유효하지 않습니다."));
        Comment comment = commentRepository.findByIdAndUserId(commentId, user.getId()).orElseThrow(
                () -> new NullPointerException("해당 댓글은 존재하지 않습니다.")
        );
        if (user.getRoleEnum() == UserRoleEnum.ADMIN || user.getId().equals(post.getUser().getId())) {
            commentRepository.deleteById(commentId);
            return commentId;
        } else {
            throw new IllegalArgumentException("삭제 권한 없음");
        }
    }
}
