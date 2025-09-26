package com.yunsu.fitmotion.notification.Controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationResponse {
    private int code;
    private String message;
}
