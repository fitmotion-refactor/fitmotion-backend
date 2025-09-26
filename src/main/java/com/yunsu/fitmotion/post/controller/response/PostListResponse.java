package com.yunsu.fitmotion.post.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PostListResponse {
    private int code;
    private String message;
    private List<PostResponse> posts;
}
