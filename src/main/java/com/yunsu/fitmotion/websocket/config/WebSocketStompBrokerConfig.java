package com.yunsu.fitmotion.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket + STOMP 설정 클래스
 *
 * - STOMP(Simple Text Oriented Messaging Protocol) 프로토콜을 사용해
 *   pub/sub 기반의 메시징을 구현한다.
 * - 메시지 브로커, 엔드포인트, prefix 등을 설정한다.
 */
@Configuration
@EnableWebSocketMessageBroker   // STOMP 기반 WebSocket 메시지 브로커 활성화
public class WebSocketStompBrokerConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 메시지 브로커 설정
     * - 클라이언트가 메시지를 구독/발행할 때 사용할 prefix를 정의한다.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 구독용(prefix): 서버 → 클라이언트 방향 메시지 전달
        // 클라이언트가 "/sub/..." 를 구독하면 해당 토픽의 메시지를 push 받는다.
        config.enableSimpleBroker("/sub");

        // 발행용(prefix): 클라이언트 → 서버 방향 메시지 전송
        // 클라이언트가 "/pub/..." 로 메시지를 보내면 @MessageMapping 메서드로 라우팅된다.
        config.setApplicationDestinationPrefixes("/pub");
    }

    /**
     * STOMP 엔드포인트 설정
     * - 클라이언트가 최초 WebSocket 연결을 시도할 endpoint를 등록한다.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp") // WebSocket 연결 엔드포인트 (ws://localhost:8080/ws-stomp)
                .setAllowedOrigins("http://localhost:3000") // CORS 허용 (프론트엔드 개발 서버)
                .withSockJS(); // WebSocket 미지원 브라우저 대응 (SockJS fallback)
    }
}
