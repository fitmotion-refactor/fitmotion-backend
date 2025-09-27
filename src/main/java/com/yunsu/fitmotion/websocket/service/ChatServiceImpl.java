package com.yunsu.fitmotion.websocket.service;

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

    @Override
    public ChatMessageDto saveMessage(ChatMessageDto dto) {
        ChatMessage chatMessage = ChatMessage.builder()
                .senderId(dto.getSenderId())
                .receiverId(dto.getReceiverId())
                .content(dto.getContent())
                .timestamp(LocalDateTime.now())
                .build();

        ChatMessage saved = chatMessageRepository.save(chatMessage);

        log.info("채팅 저장 - senderId: {}, receiverId: {}, content: {}",
                saved.getSenderId(), saved.getReceiverId(), saved.getContent());

        return ChatMessageDto.builder()
                .senderId(saved.getSenderId())
                .receiverId(saved.getReceiverId())
                .content(saved.getContent())
                .timestamp(saved.getTimestamp().toString())
                .build();
    }

    @Override
    public List<ChatMessageDto> getChatHistory(Long userId1, Long userId2) {
        return chatMessageRepository.findByUserPair(userId1, userId2).stream()
                .map(msg -> ChatMessageDto.builder()
                        .senderId(msg.getSenderId())
                        .receiverId(msg.getReceiverId())
                        .content(msg.getContent())
                        .timestamp(msg.getTimestamp().toString())
                        .build())
                .collect(Collectors.toList());
    }
}