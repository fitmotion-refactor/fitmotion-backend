package com.yunsu.fitmotion.trainer.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TrainerResponse {
    private Long id;
    private String name;
    private String specialization;
}
