package com.dcm.message.consumer;

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

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String pubMessage = redisTemplate.getStringSerializer().deserialize(message.getBody());
            log.info("Redis Subscribe Message: {}", pubMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
