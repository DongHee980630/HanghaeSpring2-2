package com.sparta.springjwt.hanghaepost.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class likeNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = true)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = true)
    private Comment comment;

    @Column(nullable = false)
    private boolean status;

    public likeNumber(User user, Post post) {
        this.user = user;
        this.post = post;
        this.status = true;
    }
    public likeNumber(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
        this.status = true;
    }


    public void disLike(Post post) {
        this.status = false;
        post.setLikeNumber(post.getLikeNumber()- 1);
    }

    public void disLike(Comment comment){
        this.status = false;
        comment.setLikeNumber(comment.getLikeNumber()- 1);
    }


}
