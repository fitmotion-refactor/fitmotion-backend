package com.yunsu.fitmotion.websocket.infrastructure;

import com.yunsu.fitmotion.websocket.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageJpaRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findBySenderIdOrReceiverId(Long senderId, Long receiverId);
}
