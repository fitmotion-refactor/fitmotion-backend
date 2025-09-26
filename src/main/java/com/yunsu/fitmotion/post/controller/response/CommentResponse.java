package com.yunsu.fitmotion.post.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponse {
    private int code;
    private String message;
    private Long commentId;
}
