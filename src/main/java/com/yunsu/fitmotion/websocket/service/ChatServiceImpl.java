package com.yunsu.fitmotion.websocket.service;

import com.yunsu.fitmotion.websocket.domain.ChatMessage;
import com.yunsu.fitmotion.websocket.dto.ChatMessageDto;
import com.yunsu.fitmotion.websocket.service.port.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatMessageRepository chatMessageRepository;

    @Override
    public ChatMessageDto saveMessage(ChatMessageDto dto) {
        ChatMessage chatMessage = ChatMessage.builder()
                .senderId(dto.getSenderId())
                .receiverId(dto.getReceiverId())
                .senderRole(dto.getSenderRole())
                .content(dto.getContent())
                .timestamp(LocalDateTime.now())
                .build();

        ChatMessage saved = chatMessageRepository.save(chatMessage);

        return ChatMessageDto.builder()
                .senderId(saved.getSenderId())
                .receiverId(saved.getReceiverId())
                .senderRole(saved.getSenderRole())
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
                        .senderRole(msg.getSenderRole())
                        .content(msg.getContent())
                        .timestamp(msg.getTimestamp().toString())
                        .build())
                .collect(Collectors.toList());
    }
}