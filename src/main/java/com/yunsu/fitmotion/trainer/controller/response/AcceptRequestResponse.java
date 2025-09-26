package com.yunsu.fitmotion.trainer.controller.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AcceptRequestResponse {
    private int code;
    private String message;
}

