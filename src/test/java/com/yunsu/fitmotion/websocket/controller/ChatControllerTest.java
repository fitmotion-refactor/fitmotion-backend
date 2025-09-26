package com.yunsu.fitmotion.websocket.controller;

import com.yunsu.fitmotion.websocket.dto.ChatMessageDto;
import com.yunsu.fitmotion.websocket.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ChatControllerTest {

    private final SimpMessagingTemplate messagingTemplate = mock(SimpMessagingTemplate.class);
    private final ChatService chatService = mock(ChatService.class);

    private final ChatController chatController = new ChatController(messagingTemplate, chatService);

    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(chatController).build();

    @Test
    void 메시지를_보내면_서비스와_메시지브로커가_호출된다() {
        // given
        ChatMessageDto dto = ChatMessageDto.builder()
                .senderId(1L).receiverId(2L).content("Hello").build();

        when(chatService.saveMessage(any(ChatMessageDto.class))).thenReturn(dto);

        // when
        chatController.send(dto);

        // then
        verify(chatService, times(1)).saveMessage(dto);
        verify(messagingTemplate, times(1)).convertAndSend(eq("/sub/chat/2"), eq(dto));
    }

    @Test
    void 채팅기록을_조회하면_정상적으로_200응답을_반환한다() throws Exception {
        when(chatService.getChatHistory(1L, 2L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/chat/history/1/2"))
                .andExpect(status().isOk());

        verify(chatService, times(1)).getChatHistory(1L, 2L);
    }
}
