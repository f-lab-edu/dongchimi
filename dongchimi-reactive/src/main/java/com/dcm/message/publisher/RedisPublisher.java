package com.dcm.message.publisher;

import com.dcm.global.config.RedisConfig;
import com.dcm.message.dto.MessageRequest;
import com.dcm.message.exception.MessagePublishException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisPublisher implements MessagePublisher {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void publish(MessageRequest request) {
        try {
            String message = objectMapper.writeValueAsString(request);
            redisTemplate.convertAndSend(RedisConfig.SUBSCRIBE_PREFIX + request.chatId(), message);
        } catch (JsonProcessingException e) {
            throw new MessagePublishException(e.getMessage());
        }
    }

}
