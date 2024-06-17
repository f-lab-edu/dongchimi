package com.dcm.message.handler;

import com.dcm.chat.service.ChatService;
import com.dcm.global.config.RedisConfig;
import com.dcm.message.dto.ChatMessageRequest;
import com.dcm.message.exception.MessageBodyException;
import com.dcm.message.exception.MessageConsumerException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageHandler implements RedisMessageHandler {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    public void handleMessage(Message message) {
        try {
            String pubMessage = deserializeMessageBody(message);
            ChatMessageRequest chatMessageRequest = objectMapper.readValue(pubMessage, ChatMessageRequest.class);
            chatService.createChatMessage(chatMessageRequest.chatId(), chatMessageRequest.email(), chatMessageRequest.message());
        } catch (Exception e) {
            throw new MessageConsumerException(e.getMessage());
        }
    }

    @Override
    public String deserializeMessageBody(Message message) {
        String body = redisTemplate.getStringSerializer().deserialize(message.getBody());
        if (!StringUtils.hasText(body)) {
            throw new MessageBodyException();
        }
        return body;
    }

    @Override
    public String getChannelPattern() {
        return RedisConfig.CHAT_SUBSCRIBE_PREFIX;
    }
}
