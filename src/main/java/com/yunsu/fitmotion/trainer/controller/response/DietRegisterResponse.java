package com.yunsu.fitmotion.trainer.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DietRegisterResponse {
    private int code;
    private String message;
}
