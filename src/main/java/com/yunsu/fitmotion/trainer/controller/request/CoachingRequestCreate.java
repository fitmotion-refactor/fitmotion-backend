package com.yunsu.fitmotion.trainer.controller.request;

import com.yunsu.fitmotion.trainer.domain.CoachingRequest;
import com.yunsu.fitmotion.trainer.domain.CoachingStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoachingRequestCreate {
    private Long userId;
    private Long trainerId;
    private CoachingStatus status;

    @Builder
    public CoachingRequestCreate(Long userId, Long trainerId, CoachingStatus status) {
        this.userId = userId;
        this.trainerId = trainerId;
        this.status = (status != null) ? status : CoachingStatus.PENDING;
    }

    public CoachingRequestCreate toDomain() {
        return CoachingRequestCreate.builder()
                .userId(userId)
                .trainerId(trainerId)
                .status(CoachingStatus.PENDING) // 기본 상태는 PENDING
                .build();
    }
}


