package com.yunsu.fitmotion.notification.Controller;

import com.yunsu.fitmotion.notification.Controller.response.NotificationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @PutMapping
    public ResponseEntity<NotificationResponse> setNotification() {
        NotificationResponse response = NotificationResponse.builder()
                .code(200)
                .message("Notification settings updated successfully.")
                .build();
        return ResponseEntity.ok(response);
    }
}