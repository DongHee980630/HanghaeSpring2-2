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
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "POST_ID", nullable = false)
    private Post post;

    public Comment(CommentRequestDto requestsDto, User user, Post post) {
        this.content = requestsDto.getContent();
        this.user = user;
        user.getCommentList().add(this);
        this.post = post;
        post.getCommentList().add(this);
    }
    public void update(CommentRequestDto requestsDto) {
        this.content = requestsDto.getContent();
    }

}
