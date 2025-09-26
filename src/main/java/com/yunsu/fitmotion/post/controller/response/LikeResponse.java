package com.yunsu.fitmotion.post.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeResponse {
    private int code;
    private String message;
    private int likeCount;
}
