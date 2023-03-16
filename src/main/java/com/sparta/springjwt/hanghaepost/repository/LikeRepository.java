package com.sparta.springjwt.hanghaepost.repository;

import com.sparta.springjwt.hanghaepost.entity.Comment;
import com.sparta.springjwt.hanghaepost.entity.likeNumber;
import com.sparta.springjwt.hanghaepost.entity.Post;
import com.sparta.springjwt.hanghaepost.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<likeNumber, Long> {

    likeNumber findByPostAndUser(Post post, User user);

    likeNumber findByCommentAndUser(Comment comment, User user);
}
