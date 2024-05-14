package com.dcm.message.controller;

import com.dcm.message.dto.MessageRequest;
import com.dcm.message.publisher.RedisPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final RedisPublisher redisPublisher;

    @MessageMapping("/chat/message")
    public void message(MessageRequest request) {
        redisPublisher.publish(request);
    }

}
