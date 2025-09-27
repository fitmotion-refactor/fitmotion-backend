//package com.yunsu.fitmotion.websocket.service;
//
//import com.yunsu.fitmotion.websocket.domain.ChatMessage;
//import com.yunsu.fitmotion.websocket.dto.ChatMessageDto;
//import com.yunsu.fitmotion.websocket.service.port.ChatMessageRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//
//class ChatServiceImplTest {
//
//    private ChatMessageRepository chatMessageRepository;
//    private ChatService chatService;
//
//    @BeforeEach
//    void setUp() {
//        chatMessageRepository = mock(ChatMessageRepository.class);
//        chatService = new ChatServiceImpl(chatMessageRepository);
//    }
//
//    @Test
//    void 메시지를_저장하면_DTO로_반환된다() {
//        // given
//        ChatMessageDto dto = ChatMessageDto.builder()
//                .senderId(1L).receiverId(2L).senderRole("USER").content("안녕하세요")
//                .build();
//
//        ChatMessage savedEntity = ChatMessage.builder()
//                .messageId(100L).senderId(1L).receiverId(2L).content("안녕하세요")
//                .timestamp(LocalDateTime.now()).build();
//
//        when(chatMessageRepository.save(any(ChatMessage.class))).thenReturn(savedEntity);
//
//        // when
//        ChatMessageDto result = chatService.saveMessage(dto);
//
//        // then
//        assertThat(result.getSenderId()).isEqualTo(1L);
//        assertThat(result.getReceiverId()).isEqualTo(2L);
//        assertThat(result.getContent()).isEqualTo("안녕하세요");
//        verify(chatMessageRepository, times(1)).save(any(ChatMessage.class));
//    }
//
//    @Test
//    void 두_사용자간의_채팅기록을_조회한다() {
//        // given
//        ChatMessage msg1 = ChatMessage.builder()
//                .messageId(1L).senderId(1L).receiverId(2L).content("첫 메시지")
//                .timestamp(LocalDateTime.now()).build();
//
//        ChatMessage msg2 = ChatMessage.builder()
//                .messageId(2L).senderId(2L).receiverId(1L).content("답장")
//                .timestamp(LocalDateTime.now()).build();
//
//        when(chatMessageRepository.findByUserPair(1L, 2L)).thenReturn(Arrays.asList(msg1, msg2));
//
//        // when
//        List<ChatMessageDto> result = chatService.getChatHistory(1L, 2L);
//
//        // then
//        assertThat(result).hasSize(2);
//        assertThat(result.get(0).getContent()).isEqualTo("첫 메시지");
//        assertThat(result.get(1).getSenderRole()).isEqualTo("TRAINER");
//    }
//}
