package com.dcm.global.config;

import com.dcm.message.consumer.RedisConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisConfig {

    public static final String CHAT_SUBSCRIBE_PREFIX = "/sub/room-";
    public static final String LIKE_PARTY_SUBSCRIBE_PREFIX = "/sub/like/party-";

    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory,
                                                        RedisConsumer consumer) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        container.addMessageListener(consumer, new PatternTopic(CHAT_SUBSCRIBE_PREFIX + "*"));
        container.addMessageListener(consumer, new PatternTopic(LIKE_PARTY_SUBSCRIBE_PREFIX + "*"));

        return container;
    }

}
