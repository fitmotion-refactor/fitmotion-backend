package com.yunsu.fitmotion.springjwt.controller.response;

import lombok.Builder;
import lombok.Getter;

/**
 * 회원가입 응답 DTO
 */
@Getter
@Builder
public class JoinResponse {
    private int code;
    private String message;
}
