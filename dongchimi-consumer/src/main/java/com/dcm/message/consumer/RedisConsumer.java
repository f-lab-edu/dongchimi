package com.dcm.message.consumer;

import com.dcm.message.handler.RedisMessageHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RedisConsumer implements MessageConsumer, MessageListener {

    private final Map<String, RedisMessageHandler> handler;

    public RedisConsumer(List<RedisMessageHandler> handlers) {
        Map<String, RedisMessageHandler> handler = new HashMap<>();
        handlers.forEach(h -> handler.put(h.getChannelPattern(), h));
        this.handler = handler;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(pattern);
        handler.keySet().forEach(key -> {
            if (channel.startsWith(key)) {
                handler.get(key).handleMessage(message);
            }
        });
    }

}
