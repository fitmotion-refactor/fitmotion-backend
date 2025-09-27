package com.yunsu.fitmotion.trainer.controller.port;

import com.yunsu.fitmotion.trainer.domain.CoachingRequest;
import com.yunsu.fitmotion.trainer.controller.request.CoachingRequestCreate;

import java.util.List;

public interface TrainerService {
    CoachingRequest create(CoachingRequestCreate create);
    List<CoachingRequest> getRequestsByTrainer(Long trainerId);
    List<CoachingRequest> getRequestsByUser(Long userId);
    CoachingRequest accept(Long id);
    CoachingRequest reject(Long id, String comment);
}