package com.dcm.message.handler;

import com.dcm.global.config.RedisConfig;
import com.dcm.message.dto.PartyLikePublishRequest;
import com.dcm.message.exception.MessageBodyException;
import com.dcm.message.exception.MessageConsumerException;
import com.dcm.party.domain.Party;
import com.dcm.party.domain.repository.PartyRepository;
import com.dcm.party.exception.NotFoundPartyException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class PartyLikeMessageHandler implements RedisMessageHandler {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;
    private final PartyRepository partyRepository;

    @Override
    public void handleMessage(Message message) {
        try {
            String pubMessage = deserializeMessageBody(message);
            PartyLikePublishRequest partyLikePublishRequest = objectMapper.readValue(pubMessage, PartyLikePublishRequest.class);
            incrementPartyLike(partyLikePublishRequest);
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
        return RedisConfig.LIKE_PARTY_SUBSCRIBE_PREFIX;
    }

    private void incrementPartyLike(PartyLikePublishRequest request) {
        Boolean hasKey = redisTemplate.hasKey(request.key());
        if (Boolean.FALSE.equals(hasKey)) {
            Party party = partyRepository.findById(request.partyId())
                .orElseThrow(() -> new NotFoundPartyException(request.partyId()));
            redisTemplate.opsForValue().set(request.key(), String.valueOf(party.getVote()));
        }
        Long like = redisTemplate.opsForValue().increment(request.key());
        System.out.println(like);
    }
}
