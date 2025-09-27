package com.yunsu.fitmotion.trainer.service.port;

import com.yunsu.fitmotion.trainer.domain.CoachingRequest;
import com.yunsu.fitmotion.trainer.domain.CoachingStatus;

import java.util.List;
import java.util.Optional;

public interface CoachingRequestRepository {
    CoachingRequest save(CoachingRequest request);
    Optional<CoachingRequest> findById(Long id);
    List<CoachingRequest> findByTrainerId(Long trainerId);
    List<CoachingRequest> findByUserId(Long userId);

    boolean existsByUserIdAndTrainerIdAndStatus(Long userId, Long trainerId, CoachingStatus status);
}
