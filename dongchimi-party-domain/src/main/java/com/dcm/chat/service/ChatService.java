package com.dcm.chat.service;

import com.dcm.chat.domain.Chat;
import com.dcm.chat.domain.ChatMessage;
import com.dcm.chat.exception.NotFoundChatException;
import com.dcm.chat.domain.repository.ChatMessageRepository;
import com.dcm.chat.domain.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public void createChatMessage(Long chatId, String email, String message) {
        Chat chat = readChat(chatId);
        ChatMessage chatMessage = new ChatMessage(email, message, chat);
        chatMessageRepository.save(chatMessage);
    }

    public Chat readChat(Long chatId) {
        return chatRepository.findById(chatId).orElseThrow(() -> new NotFoundChatException(chatId));
    }
}
