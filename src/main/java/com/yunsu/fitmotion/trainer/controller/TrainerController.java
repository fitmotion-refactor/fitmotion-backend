package com.yunsu.fitmotion.trainer.controller;


import com.yunsu.fitmotion.trainer.controller.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

    @GetMapping
    public ResponseEntity<List<TrainerResponse>> getTrainers() {
        List<TrainerResponse> trainers = List.of(
                TrainerResponse.builder()
                        .id(1L)
                        .name("홍길동")
                        .specialization("PT")
                        .build()
        );
        return ResponseEntity.ok(trainers);
    }

    @PostMapping("/request")
    public ResponseEntity<CoachingRequestResponse> requestCoaching() {
        return ResponseEntity.ok(
                CoachingRequestResponse.builder()
                        .code(200)
                        .message("Coaching request sent successfully.")
                        .comment("comment")
                        .build()
        );
    }

    @PostMapping("/send")
    public ResponseEntity<CoachingRequestResponse> sendCoaching() {
        return ResponseEntity.ok(
                CoachingRequestResponse.builder()
                        .code(200)
                        .message("Coaching request sent successfully.")
                        .comment("comment")
                        .build()
        );
    }

    @GetMapping("/requests")
    public ResponseEntity<CoachingRequestResponse> getTrainerRequests() {
        return ResponseEntity.ok(
                CoachingRequestResponse.builder()
                        .code(200)
                        .message("Trainer requests retrieved successfully.")
                        .comment("data")
                        .build()
        );
    }

    @GetMapping("/user-requests")
    public ResponseEntity<CoachingRequestResponse> getUserRequests() {
        return ResponseEntity.ok(
                CoachingRequestResponse.builder()
                        .code(200)
                        .message("User requests retrieved successfully.")
                        .comment("data")
                        .build()
        );
    }

    @PatchMapping("/accept")
    public ResponseEntity<AcceptRequestResponse> acceptRequest() {
        return ResponseEntity.ok(
                AcceptRequestResponse.builder()
                        .code(200)
                        .message("Coaching request accepted successfully.")
                        .build()
        );
    }

    @PostMapping("/diet")
    public ResponseEntity<DietRegisterResponse> registerDiet() {
        return ResponseEntity.ok(
                DietRegisterResponse.builder()
                        .code(200)
                        .message("Diet registered successfully.")
                        .build()
        );
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<TrainerIdResponse> getTrainerIdByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(
                TrainerIdResponse.builder()
                        .code(200)
                        .message("Trainer ID retrieved successfully.")
                        .trainerId(userId)
                        .build()
        );
    }
}