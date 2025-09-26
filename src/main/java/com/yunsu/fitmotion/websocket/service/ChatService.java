package com.yunsu.fitmotion.websocket.service;

import com.yunsu.fitmotion.websocket.dto.ChatMessageDto;

import java.util.List;

public interface ChatService {
    ChatMessageDto saveMessage(ChatMessageDto dto);
    List<ChatMessageDto> getChatHistory(Long userId1, Long userId2);
}