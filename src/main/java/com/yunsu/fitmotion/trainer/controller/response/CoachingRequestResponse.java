package com.yunsu.fitmotion.trainer.controller.response;

import com.yunsu.fitmotion.trainer.domain.CoachingRequest;
import com.yunsu.fitmotion.trainer.domain.CoachingStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CoachingRequestResponse {

    private final Long id;
    private final Long userId;
    private final Long trainerId;
    private final CoachingStatus status;
    private final String comment;

    @Builder
    public CoachingRequestResponse(Long id, Long userId, Long trainerId, CoachingStatus status, String comment) {
        this.id = id;
        this.userId = userId;
        this.trainerId = trainerId;
        this.status = status;
        this.comment = comment;
    }

    public static CoachingRequestResponse from(CoachingRequest domain) {
        return CoachingRequestResponse.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .trainerId(domain.getTrainerId())
                .status(domain.getStatus())
                .comment(domain.getComment())
                .build();
    }
}
