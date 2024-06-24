package com.dcm.message.handler;

import com.dcm.global.config.RedisConfig;
import com.dcm.message.exception.MessageBodyException;
import com.dcm.party.context.PartyLikeContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class PartyLikeMessageHandler implements RedisMessageHandler {

    private final RedisTemplate<String, String> redisTemplate;
    private final PartyLikeContext partyLikeContext;

    @Override
    public void handleMessage(Message message) {
        String partyId = deserializeMessageBody(message);
        partyLikeContext.setPartyLikeRequest(Long.valueOf(partyId));
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
        return RedisConfig.LIKE_PARTY_SUBSCRIBE_PREFIX;
    }
}
