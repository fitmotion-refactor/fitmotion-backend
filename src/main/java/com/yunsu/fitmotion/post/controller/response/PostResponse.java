package com.yunsu.fitmotion.post.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String photoUrl;
}