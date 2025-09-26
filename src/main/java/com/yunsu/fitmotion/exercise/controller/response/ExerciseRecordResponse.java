package com.yunsu.fitmotion.exercise.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExerciseRecordResponse {
    private int code;
    private String message;
}
