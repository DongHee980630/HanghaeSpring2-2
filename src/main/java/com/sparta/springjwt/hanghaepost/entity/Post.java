package com.sparta.springjwt.hanghaepost.entity;


import com.sparta.springjwt.hanghaepost.dto.PostRequestsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long userId;

    public Post(PostRequestsDto requestsDto, Long userId) {
        this.title = requestsDto.getTitle();
        this.contents = requestsDto.getContents();
        this.userId = userId;
    }

    public void update(PostRequestsDto requestsDto) {
        this.title = requestsDto.getTitle();
        this.contents = requestsDto.getContents();
    }
}
