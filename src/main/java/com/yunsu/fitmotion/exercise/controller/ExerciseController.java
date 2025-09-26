package com.yunsu.fitmotion.exercise.controller;

import com.yunsu.fitmotion.exercise.controller.response.ExerciseListResponse;
import com.yunsu.fitmotion.exercise.controller.response.ExerciseRecordResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    @PostMapping
    public ResponseEntity<ExerciseRecordResponse> recordExercise() {
        ExerciseRecordResponse response = ExerciseRecordResponse.builder()
                .code(200)
                .message("Exercise log saved successfully.")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ExerciseListResponse> getExercises() {
        ExerciseListResponse.Exercise exercise = ExerciseListResponse.Exercise.builder()
                .name("squat")
                .date("2025-01-01")
                .durationMinutes(30)
                .caloriesBurned(100)
                .build();

        ExerciseListResponse response = ExerciseListResponse.builder()
                .code(200)
                .message("Successfully queried exercise records.")
                .data(List.of(exercise))
                .build();

        return ResponseEntity.ok(response);
    }
}