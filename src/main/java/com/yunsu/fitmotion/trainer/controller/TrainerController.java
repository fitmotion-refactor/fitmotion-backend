package com.yunsu.fitmotion.trainer.controller;

import com.yunsu.fitmotion.springjwt.jwt.JWTUtil;
import com.yunsu.fitmotion.trainer.controller.port.TrainerService;
import com.yunsu.fitmotion.trainer.controller.response.CoachingRequestResponse;
import com.yunsu.fitmotion.trainer.controller.request.CoachingRequestCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 트레이너 관련 API 컨트롤러
 * - 유저가 트레이너에게 코칭 요청 생성
 * - 트레이너/유저별 요청 조회
 * - 요청 수락/거절 처리
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trainers")
@Slf4j
public class TrainerController {

    private final TrainerService trainerService;
    private final JWTUtil jwtUtil;

    /**
     * 1. 코칭 요청 생성 (유저 -> 트레이너)
     * Header: Authorization: Bearer <jwt>
     * Body: { "trainerId": 101 }
     */
    @PostMapping("/request")
    public ResponseEntity<CoachingRequestResponse> requestTrainer(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody CoachingRequestCreate request
    ) {
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtUtil.getUserId(token);

        request.setUserId(userId);

        log.info("코칭 요청 생성 요청 - userId={}, trainerId={}", request.getUserId(), request.getTrainerId());

        CoachingRequestResponse response = CoachingRequestResponse.from(
                trainerService.create(request.toDomain())
        );

        return ResponseEntity.ok(response);
    }

    /**
     * 2. 트레이너가 받은 모든 요청 조회
     * PathVariable: trainerId
     */
    @GetMapping("/requests/{trainerId}")
    public ResponseEntity<List<CoachingRequestResponse>> getTrainerRequests(@PathVariable Long trainerId) {
        log.info("트레이너 요청 목록 조회 - trainerId={}", trainerId);
        List<CoachingRequestResponse> responses = trainerService.getRequestsByTrainer(trainerId)
                .stream()
                .map(CoachingRequestResponse::from)
                .toList();
        log.info("트레이너 요청 목록 조회 완료 - count={}", responses.size());
        return ResponseEntity.ok(responses);
    }

    /**
     * 3. 트레이너가 요청 수락
     * PathVariable: requestId
     */
    @PatchMapping("/accept/{id}")
    public ResponseEntity<CoachingRequestResponse> acceptRequest(@PathVariable Long id) {
        log.info("코칭 요청 수락 시도 - requestId={}", id);
        CoachingRequestResponse response = CoachingRequestResponse.from(trainerService.accept(id));
        log.info("코칭 요청 수락 완료 - requestId={}", response.getId());
        return ResponseEntity.ok(response);
    }

    /**
     * 4. 트레이너가 요청 거절
     * PathVariable: requestId, RequestParam: comment
     */
    @PatchMapping("/reject/{id}")
    public ResponseEntity<CoachingRequestResponse> rejectRequest(@PathVariable Long id, @RequestParam String comment) {
        log.info("코칭 요청 거절 시도 - requestId={}, comment={}", id, comment);
        CoachingRequestResponse response = CoachingRequestResponse.from(trainerService.reject(id, comment));
        log.info("코칭 요청 거절 완료 - requestId={}", response.getId());
        return ResponseEntity.ok(response);
    }

    /**
     * 5. 유저가 보낸 모든 요청 조회
     * PathVariable: userId
     */
    @GetMapping("/user-requests/{userId}")
    public ResponseEntity<List<CoachingRequestResponse>> getUserRequests(@PathVariable Long userId) {
        log.info("유저 요청 목록 조회 - userId={}", userId);
        List<CoachingRequestResponse> responses = trainerService.getRequestsByUser(userId)
                .stream()
                .map(CoachingRequestResponse::from)
                .toList();
        log.info("유저 요청 목록 조회 완료 - count={}", responses.size());
        return ResponseEntity.ok(responses);
    }

}

