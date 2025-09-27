package com.yunsu.fitmotion.trainer.domain;

import com.yunsu.fitmotion.trainer.controller.request.CoachingRequestCreate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CoachingRequest {

    private final Long id;
    private final Long userId;
    private final Long trainerId;
    private final CoachingStatus status;
    private final String comment;

    @Builder
    public CoachingRequest(Long id, Long userId, Long trainerId, CoachingStatus status, String comment) {
        this.id = id;
        this.userId = userId;
        this.trainerId = trainerId;
        this.status = status;
        this.comment = comment;
    }

    public static CoachingRequest from(CoachingRequestCreate create) {
        return CoachingRequest.builder()
                .userId(create.getUserId())
                .trainerId(create.getTrainerId())
                .build();
    }

    public CoachingRequest accept() {
        return CoachingRequest.builder()
                .id(id).userId(userId).trainerId(trainerId)
                .status(CoachingStatus.ACCEPTED)
                .comment(comment)
                .build();
    }

    public CoachingRequest reject(String comment) {
        return CoachingRequest.builder()
                .id(id).userId(userId).trainerId(trainerId)
                .status(CoachingStatus.REJECTED)
                .comment(comment)
                .build();
    }
}

