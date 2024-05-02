package com.dcm.message.publisher;

public interface MessagePublisher {

    void publish(String topic, String message);

}
