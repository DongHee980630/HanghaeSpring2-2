package com.sparta.springjwt.hanghaepost.repository;

import com.sparta.springjwt.hanghaepost.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
