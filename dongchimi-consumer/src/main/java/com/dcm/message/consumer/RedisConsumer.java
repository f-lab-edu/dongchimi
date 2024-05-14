package com.dcm.message.consumer;

import com.dcm.message.dto.MessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisConsumer implements MessageListener {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String pubMessage = redisTemplate.getStringSerializer().deserialize(message.getBody());
            MessageRequest messageRequest = objectMapper.readValue(pubMessage, MessageRequest.class);

            // TODO message 정보 저장
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }

}
