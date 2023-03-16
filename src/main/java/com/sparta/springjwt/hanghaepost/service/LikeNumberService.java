package com.sparta.springjwt.hanghaepost.service;

import com.sparta.springjwt.hanghaepost.dto.ResponseDto;
import com.sparta.springjwt.hanghaepost.entity.Comment;
import com.sparta.springjwt.hanghaepost.entity.likeNumber;
import com.sparta.springjwt.hanghaepost.entity.Post;
import com.sparta.springjwt.hanghaepost.entity.User;
import com.sparta.springjwt.hanghaepost.repository.CommentRepository;
import com.sparta.springjwt.hanghaepost.repository.LikeRepository;
import com.sparta.springjwt.hanghaepost.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeNumberService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public ResponseDto postLike(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글 ID가 유효하지 않습니다."));
        if (likeRepository.findByPostAndUser(post,user)== null){
            post.setLikeNumber(post.getLikeNumber()+ 1);
            likeNumber like = new likeNumber(user, post); //true 처리
            likeRepository.save(like);
            return new ResponseDto(HttpStatus.OK.value(), "좋아요");
        }else {
            likeNumber like = likeRepository.findByPostAndUser(post,user);
            like.disLike(post);
            likeRepository.delete(like);
            return new ResponseDto(HttpStatus.OK.value(), "좋아요 취소");
        }
    }

    @Transactional
    public ResponseDto commentLike(Long postId, Long commentId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글 ID가 유효하지 않습니다."));
        Comment comment = commentRepository.findByIdAndPostId(commentId,postId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다.")
        );
        if (likeRepository.findByCommentAndUser(comment,user)== null){
            comment.setLikeNumber(comment.getLikeNumber()+ 1);
            likeNumber like = new likeNumber(user, comment); //true 처리
            likeRepository.save(like);
            return new ResponseDto(HttpStatus.OK.value(), "좋아요");
        }else {
            likeNumber like = likeRepository.findByCommentAndUser(comment,user);
            like.disLike(comment);
            likeRepository.delete(like);
            return new ResponseDto(HttpStatus.OK.value(), "좋아요 취소");
        }
    }
}
