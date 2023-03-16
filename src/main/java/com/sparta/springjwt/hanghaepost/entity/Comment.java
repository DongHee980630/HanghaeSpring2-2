package com.sparta.springjwt.hanghaepost.entity;

import com.sparta.springjwt.hanghaepost.dto.CommentRequestDto;
import com.sparta.springjwt.hanghaepost.dto.PostRequestsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @NotBlank
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = true)
    private int likeNumber = 0;

    public Comment(CommentRequestDto requestsDto, User user, Post post) {
        this.content = requestsDto.getContent();
        this.user = user;
        this.post = post;
        post.getCommentList().add(this);
    }
    public void update(CommentRequestDto requestsDto) {
        this.content = requestsDto.getContent();
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }
}
