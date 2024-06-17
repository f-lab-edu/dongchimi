package com.dcm.message.handler;

import org.springframework.data.redis.connection.Message;

public interface RedisMessageHandler {

    void handleMessage(Message message);
    String deserializeMessageBody(Message message);
    String getChannelPattern();

}
