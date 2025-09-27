package com.yunsu.fitmotion.websocket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class ChatMessageDto {
    private final Long senderId;      // 보낸 사람 ID
    private final Long receiverId;    // 받는 사람 ID
    private final String senderRole;  // USER / TRAINER
    private final String content;     // 메시지 내용
    private final String timestamp;   // 보낸 시간
}

