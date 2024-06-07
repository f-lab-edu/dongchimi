package com.dcm.global.config;

import com.dcm.message.consumer.RedisConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    public static final String SUBSCRIBE_PREFIX = "/sub/room-";

    /**
     * Redis Connection Factory 특징
     *  1. Jedis
     *    - 멀티쓰레드환경에서 쓰레드 안전을 보장하지 않는다.
     *    - Connection pool을 사용하여 성능, 안정성 개선이 가능하지만 Lettuce보다 상대적으로 하드웨어적인 자원이 많이 필요하다.
     *    - 비동기 기능을 제공하지 않는다.
     *
     *  2. Lettuce - Netty 기반 redis client library
     *    - 비동기로 요청하기 때문에 Jedis에 비해 높은 성능을 가지고 있다.
     *    - TPS, 자원사용량 모두 Jedis에 비해 우수한 성능을 보인다는 테스트 사례가 있다.
     * */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory,
                                                        RedisConsumer consumer) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter(consumer), new PatternTopic(SUBSCRIBE_PREFIX + "*"));
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(RedisConsumer consumer) {
        return new MessageListenerAdapter(consumer, "onMessage");
    }

}
