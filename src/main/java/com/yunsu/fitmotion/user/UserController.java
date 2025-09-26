package com.yunsu.fitmotion.user;

import com.yunsu.fitmotion.post.controller.response.PostResponse;
import com.yunsu.fitmotion.trainer.controller.response.TrainerIdResponse;
import com.yunsu.fitmotion.user.response.UserProfileResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile() {
        return ResponseEntity.ok(
                UserProfileResponse.builder()
                        .username("Trainer ID retrieved successfully.")
                        .email("example@naver.com")
                        .build()
        );
    }
}
