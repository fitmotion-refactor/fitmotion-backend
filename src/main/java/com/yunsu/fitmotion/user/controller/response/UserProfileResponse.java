package com.yunsu.fitmotion.user.controller.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserProfileResponse {
    private String username;
    private String email;
}

