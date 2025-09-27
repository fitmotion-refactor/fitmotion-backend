package com.yunsu.fitmotion.websocket.service;

import com.yunsu.fitmotion.trainer.domain.CoachingStatus;
import com.yunsu.fitmotion.trainer.service.port.CoachingRequestRepository;
import com.yunsu.fitmotion.user.infrastructure.JpaUserRepository;
import com.yunsu.fitmotion.user.infrastructure.UserEntity;
import com.yunsu.fitmotion.websocket.domain.ChatMessage;
import com.yunsu.fitmotion.websocket.dto.ChatMessageDto;
import com.yunsu.fitmotion.websocket.service.port.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final CoachingRequestRepository coachingRequestRepository;
    private final JpaUserRepository userJpaRepository;

    @Override
    public ChatMessageDto saveMessage(ChatMessageDto dto) {
        // 1. 매칭 여부 검증
        boolean userToTrainer = coachingRequestRepository.existsByUserIdAndTrainerIdAndStatus(
                dto.getSenderId(), dto.getReceiverId(), CoachingStatus.ACCEPTED
        );

        boolean trainerToUser = coachingRequestRepository.existsByUserIdAndTrainerIdAndStatus(
                dto.getReceiverId(), dto.getSenderId(), CoachingStatus.ACCEPTED
        );

        boolean matched = userToTrainer || trainerToUser;
        if (!matched) {
            throw new IllegalStateException(" 아직 코칭 요청이 수락 x"); // 추후 수정
        }

        // 2. UserEntity 조회 (sender, receiver)
        UserEntity sender = userJpaRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 senderId: " + dto.getSenderId()));
        UserEntity receiver = userJpaRepository.findById(dto.getReceiverId())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 receiverId: " + dto.getReceiverId()));

        // 3. ChatMessage 생성 및 저장
        ChatMessage chatMessage = ChatMessage.builder()
                .sender(sender)
                .receiver(receiver)
                .content(dto.getContent())
                .timestamp(LocalDateTime.now())
                .read(false)
                .build();

        ChatMessage saved = chatMessageRepository.save(chatMessage);

        log.info("채팅 저장 - senderId: {}, receiverId: {}, content={}",
                saved.getSender().getId(), saved.getReceiver().getId(), saved.getContent());

        // 4. DTO 변환 후 반환
        return ChatMessageDto.builder()
                .senderId(saved.getSender().getId())
                .receiverId(saved.getReceiver().getId())
                .senderRole(saved.getSender().getRole().name())
                .content(saved.getContent())
                .timestamp(saved.getTimestamp().toString())
                .read(saved.isRead())
                .build();
    }

    @Override
    public List<ChatMessageDto> getChatHistory(Long userId1, Long userId2) {
        return chatMessageRepository.findByUserPair(userId1, userId2).stream()
                .map(msg -> ChatMessageDto.builder()
                        .senderId(msg.getSender().getId())
                        .receiverId(msg.getReceiver().getId())
                        .senderRole(msg.getSender().getRole().name())
                        .content(msg.getContent())
                        .timestamp(msg.getTimestamp().toString())
                        .read(msg.isRead())
                        .build())
                .collect(Collectors.toList());
    }
}
