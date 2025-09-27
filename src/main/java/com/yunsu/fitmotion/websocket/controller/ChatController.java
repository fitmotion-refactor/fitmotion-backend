package com.yunsu.fitmotion.websocket.controller;

import com.yunsu.fitmotion.websocket.dto.ChatMessageDto;
import com.yunsu.fitmotion.websocket.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
@Slf4j
public class ChatController {

    private final SimpMessagingTemplate template; // Spring STOMP에서 제공하는 메시지 발송 유틸 클래스
    private final ChatService chatService;

    /**
     * 실시간 메시지 전송 + DB 저장
     */
    @MessageMapping("/chat.send")
    public void send(ChatMessageDto chatMessageDto) {
        log.info("채팅 전송 요청 - senderId={}, receiverId={}, content={}",
                chatMessageDto.getSenderId(),
                chatMessageDto.getReceiverId(),
                chatMessageDto.getContent());

        ChatMessageDto saved = chatService.saveMessage(chatMessageDto);

        log.info("채팅 저장 완료 - messageId={}, senderId={}, receiverId={}",
                saved.getSenderId(),
                saved.getReceiverId(),
                saved.getContent());

        template.convertAndSend("/sub/chat/" + saved.getReceiverId(), saved); // /sub/chat/{receiverId} 채널을 구독 중인 클라이언트에게 메시지를 푸시.

        log.info("채팅 푸시 완료 - receiverId={}, destination=/sub/chat/{}",
                saved.getReceiverId(), saved.getReceiverId());
    }

    /**
     * 채팅 기록 조회
     */
    @GetMapping("/history/{userId1}/{userId2}")
    public List<ChatMessageDto> getChatHistory(@PathVariable Long userId1,
                                               @PathVariable Long userId2) {
        log.info("채팅 기록 조회 요청 - userId1={}, userId2={}", userId1, userId2);

        List<ChatMessageDto> history = chatService.getChatHistory(userId1, userId2);

        log.info("채팅 기록 조회 완료 - count={}, userId1={}, userId2={}",
                history.size(), userId1, userId2);

        return history;
    }
}
