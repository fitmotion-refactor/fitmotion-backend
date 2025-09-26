package com.yunsu.fitmotion.exercise.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ExerciseListResponse {
    private int code;
    private String message;
    private List<Exercise> data;

    @Getter
    @Builder
    public static class Exercise {
        private String name;
        private String date;
        private int durationMinutes;
        private int caloriesBurned;
    }
}