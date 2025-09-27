package com.yunsu.fitmotion.trainer.infrastructure;

import com.yunsu.fitmotion.trainer.domain.CoachingRequest;
import com.yunsu.fitmotion.trainer.domain.CoachingStatus;
import com.yunsu.fitmotion.user.infrastructure.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coaching_requests")
public class CoachingRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    private UserEntity trainer;


    private Long userId;
    private Long trainerId;

    @Enumerated(EnumType.STRING)
    private CoachingStatus status;

    private String comment;

    public static CoachingRequestEntity from(CoachingRequest request) {
        CoachingRequestEntity entity = new CoachingRequestEntity();
        entity.id = request.getId();
        entity.userId = request.getUserId();
        entity.trainerId = request.getTrainerId();
        entity.status = request.getStatus();
        entity.comment = request.getComment();
        return entity;
    }

    public CoachingRequest toModel() {
        return CoachingRequest.builder()
                .id(id)
                .userId(userId)
                .trainerId(trainerId)
                .status(status)
                .comment(comment)
                .build();
    }
}

