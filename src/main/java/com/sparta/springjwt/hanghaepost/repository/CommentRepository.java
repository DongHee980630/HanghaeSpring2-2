package com.sparta.springjwt.hanghaepost.repository;

import com.sparta.springjwt.hanghaepost.entity.Comment;
import com.sparta.springjwt.hanghaepost.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndUserId(Long id, Long userId );

}
