package com.yunsu.fitmotion.websocket.service.port;

import com.yunsu.fitmotion.websocket.domain.ChatMessage;

import java.util.List;

public interface ChatMessageRepository {
    ChatMessage save(ChatMessage message);
    List<ChatMessage> findByUserPair(Long userId1, Long userId2);
}