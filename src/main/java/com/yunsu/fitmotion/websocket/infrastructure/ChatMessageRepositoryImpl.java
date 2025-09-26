package com.yunsu.fitmotion.websocket.infrastructure;

import com.yunsu.fitmotion.websocket.domain.ChatMessage;
import com.yunsu.fitmotion.websocket.service.port.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ChatMessageRepositoryImpl implements ChatMessageRepository {

    private final ChatMessageJpaRepository jpaRepository;

    @Override
    public ChatMessage save(ChatMessage message) {
        return jpaRepository.save(message);
    }

    @Override
    public List<ChatMessage> findByUserPair(Long userId1, Long userId2) {
        return jpaRepository.findBySenderIdOrReceiverId(userId1, userId2).stream()
                .filter(msg ->
                        (msg.getSenderId().equals(userId1) && msg.getReceiverId().equals(userId2)) ||
                                (msg.getSenderId().equals(userId2) && msg.getReceiverId().equals(userId1))
                )
                .collect(Collectors.toList());
    }
}