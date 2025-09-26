package com.yunsu.fitmotion.websocket.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private Long senderId;
    private Long receiverId;
    private String senderRole;   // USER / TRAINER
    private String content;

    private LocalDateTime timestamp;
}
