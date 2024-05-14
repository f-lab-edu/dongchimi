package com.dcm.message.publisher;

import com.dcm.message.dto.MessageRequest;

public interface MessagePublisher {

    void publish(MessageRequest request);

}
