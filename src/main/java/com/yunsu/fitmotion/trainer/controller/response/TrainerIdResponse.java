package com.yunsu.fitmotion.trainer.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TrainerIdResponse {
    private int code;
    private String message;
    private Long trainerId;
}