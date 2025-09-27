package com.yunsu.fitmotion.trainer.infrastructure;

import com.yunsu.fitmotion.trainer.domain.CoachingRequest;
import com.yunsu.fitmotion.trainer.domain.CoachingStatus;
import com.yunsu.fitmotion.trainer.service.port.CoachingRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CoachingRequestRepositoryImpl implements CoachingRequestRepository {

    private final CoachingRequestJpaRepository jpaRepository;

    @Override
    public CoachingRequest save(CoachingRequest request) {
        return jpaRepository.save(CoachingRequestEntity.from(request)).toModel();
    }

    @Override
    public Optional<CoachingRequest> findById(Long id) {
        return jpaRepository.findById(id).map(CoachingRequestEntity::toModel);
    }

    @Override
    public List<CoachingRequest> findByTrainerId(Long trainerId) {
        return jpaRepository.findAll().stream()
                .filter(e -> e.getTrainerId().equals(trainerId))
                .map(CoachingRequestEntity::toModel)
                .toList();
    }

    @Override
    public List<CoachingRequest> findByUserId(Long userId) {
        return jpaRepository.findAll().stream()
                .filter(e -> e.getUserId().equals(userId))
                .map(CoachingRequestEntity::toModel)
                .toList();
    }

    @Override
    public boolean existsByUserIdAndTrainerIdAndStatus(Long userId, Long trainerId, CoachingStatus status) {
        return jpaRepository.existsByUserIdAndTrainerIdAndStatus(userId, trainerId, status);
    }
}
