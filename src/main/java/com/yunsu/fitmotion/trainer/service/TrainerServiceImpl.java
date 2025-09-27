package com.yunsu.fitmotion.trainer.service;

import com.yunsu.fitmotion.common.domain.exception.ResourceNotFoundException;
import com.yunsu.fitmotion.trainer.controller.port.TrainerService;
import com.yunsu.fitmotion.trainer.domain.CoachingRequest;
import com.yunsu.fitmotion.trainer.controller.request.CoachingRequestCreate;
import com.yunsu.fitmotion.trainer.service.port.CoachingRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TrainerService 구현체
 * - 코칭 요청 생성, 조회, 수락/거절 비즈니스 흐름 관리
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TrainerServiceImpl implements TrainerService {

    private final CoachingRequestRepository repository;

    /**
     * 코칭 요청 생성
     */
    @Transactional
    public CoachingRequest create(CoachingRequestCreate create) {
        log.info("코칭 요청 생성 시작 - userId={}, trainerId={}", create.getUserId(), create.getTrainerId());
        CoachingRequest saved = repository.save(CoachingRequest.from(create));
        log.info("코칭 요청 생성 완료 - requestId={}", saved.getId());
        return saved;
    }

    /**
     * 특정 트레이너가 받은 요청 목록 조회
     */
    public List<CoachingRequest> getRequestsByTrainer(Long trainerId) {
        log.debug("트레이너 요청 조회 - trainerId={}", trainerId);
        return repository.findByTrainerId(trainerId);
    }

    /**
     * 특정 유저가 보낸 요청 목록 조회
     */
    public List<CoachingRequest> getRequestsByUser(Long userId) {
        log.debug("유저 요청 조회 - userId={}", userId);
        return repository.findByUserId(userId);
    }

    /**
     * 요청 수락 처리
     */
    @Transactional
    public CoachingRequest accept(Long id) {
        log.info("요청 수락 시도 - requestId={}", id);
        CoachingRequest request = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request", id));
        CoachingRequest updated = repository.save(request.accept());
        log.info("요청 수락 완료 - requestId={}", updated.getId());
        return updated;
    }

    /**
     * 요청 거절 처리 (거절 사유 포함)
     */
    @Transactional
    public CoachingRequest reject(Long id, String comment) {
        log.info("요청 거절 시도 - requestId={}, comment={}", id, comment);
        CoachingRequest request = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request", id));
        CoachingRequest updated = repository.save(request.reject(comment));
        log.info("요청 거절 완료 - requestId={}", updated.getId());
        return updated;
    }
}

