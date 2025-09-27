package com.yunsu.fitmotion.trainer.infrastructure;

import com.yunsu.fitmotion.trainer.domain.CoachingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachingRequestJpaRepository extends JpaRepository<CoachingRequestEntity, Long> {
    boolean existsByUserIdAndTrainerIdAndStatus(Long userId, Long trainerId, CoachingStatus status);
}
