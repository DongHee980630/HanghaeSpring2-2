package com.sparta.springjwt.hanghaepost.dto;

import com.sparta.springjwt.hanghaepost.entity.Comment;
import com.sparta.springjwt.hanghaepost.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String contents;

    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList = new ArrayList<>();
    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.username = post.getUser().getUsername();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        List<Comment> comments = post.getCommentList();
        if (!comments.isEmpty()) {
            List<CommentResponseDto> commentList = new ArrayList<>();
            for (Comment comment : comments) {
                commentList.add(new CommentResponseDto(comment));
            }
            this.commentList = commentList;
        }
    }
}
