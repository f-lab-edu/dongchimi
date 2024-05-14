package com.dcm.message.consumer;

import com.dcm.global.config.RedisConfig;
import com.dcm.global.serializer.Serializer;
import com.dcm.message.dto.MessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisConsumer implements MessageListener {

    private final Serializer<String> serializer; // TODO 의존 타입에 대한 정의 필요
    private final SimpMessageSendingOperations messageSendingOperations;
    private final ObjectMapper objectMapper;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String pubMessage = serializer.deserialize(message.getBody());
            MessageRequest messageRequest = objectMapper.readValue(pubMessage, MessageRequest.class);

            // TODO Client 구독자에게 Message 수신
            messageSendingOperations.convertAndSend(RedisConfig.SUBSCRIBE_PREFIX + messageRequest.chatId(), messageRequest.message());
        } catch (Exception e) {
            // TODO Exception Handler 통해 예외 처리
            log.warn(e.getMessage(), e);
        }
    }

}
