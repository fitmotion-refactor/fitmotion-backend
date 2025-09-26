package com.yunsu.fitmotion.trainer.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CoachingRequestResponse {
    private int code;
    private String message;
    private String comment;
}
