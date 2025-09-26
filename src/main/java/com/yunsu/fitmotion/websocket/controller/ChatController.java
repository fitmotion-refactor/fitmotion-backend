package com.yunsu.fitmotion.websocket.controller;

import com.yunsu.fitmotion.websocket.dto.ChatMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController   // WebSocket 메시지 처리 컨트롤러
public class ChatController {
    private final SimpMessagingTemplate template; // STOMP를 이용해 특정 채널/사용자에게 메시지 전송

    @Autowired
    public ChatController(SimpMessagingTemplate template) {
        this.template = template;
    }

    /**
     * 클라이언트가 "/pub/messages"로 메시지를 보내면 실행됨
     * → 구독 중인 "/sub/message" 채널의 모든 사용자에게 메시지 전달
     */
    @MessageMapping("/messages")
    public ChatMessageDto send2(@RequestBody ChatMessageDto chatMessageDto) {
        template.convertAndSend("/sub/message", chatMessageDto.getContent());
        return chatMessageDto; // 클라이언트에 echo 응답
    }
}
