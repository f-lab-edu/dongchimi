package com.dcm.chat.service;

import com.dcm.chat.domain.Chat;
import com.dcm.chat.domain.ChatMessage;
import com.dcm.chat.domain.repository.ChatMessageRepository;
import com.dcm.chat.domain.repository.ChatRepository;
import com.dcm.fixtures.PartyFixtures;
import com.dcm.global.annotation.ServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ServiceTest
public class ChatServiceTest {

    @Mock
    private ChatRepository chatRepository;

    @Mock
    private ChatMessageRepository chatMessageRepository;

    @InjectMocks
    private ChatService chatService;

    @DisplayName("성공적으로 채팅 메세지를 저장한다.")
    @Test
    void successChatMessage() {
        // given
        Optional<Chat> chat = Optional.of(PartyFixtures.CREATE_CHAT());
        ChatMessage chatMessage = PartyFixtures.CREATE_CHAT_MESSAGE();

        // when
        doReturn(chat).when(chatRepository).findById(any(Long.class));
        doReturn(chatMessage).when(chatMessageRepository).save(any(ChatMessage.class));
        chatService.createChatMessage(PartyFixtures.CHAT_ID, PartyFixtures.EMAIL, PartyFixtures.MESSAGE);

        // then
        verify(chatRepository, times(1)).findById(any(Long.class));
        verify(chatMessageRepository, times(1)).save(any(ChatMessage.class));
    }

}
