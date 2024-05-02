package com.dcm.message.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPublisher implements MessagePublisher {

    private final StringRedisTemplate redisTemplate;
    private final SimpMessageSendingOperations messageSendingOperations;

    @Override
    public void publish(String partyId, String message) {
        redisTemplate.convertAndSend("/sub/room-" + partyId, message);
        messageSendingOperations.convertAndSend("/sub/room-" + partyId, message);
    }

}
