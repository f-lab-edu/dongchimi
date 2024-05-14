package com.dcm.global.serializer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisSerializer implements Serializer {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public Object deserialize(byte[] bytes) {
        return redisTemplate.getStringSerializer().deserialize(bytes);
    }
}
