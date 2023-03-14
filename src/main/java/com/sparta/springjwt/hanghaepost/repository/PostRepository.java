package com.sparta.springjwt.hanghaepost.repository;

import com.sparta.springjwt.hanghaepost.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();

    Optional<Post> findByIdAndUserId(Long id,Long userId );
}
