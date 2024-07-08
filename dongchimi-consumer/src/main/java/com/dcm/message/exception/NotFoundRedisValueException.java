package com.dcm.message.exception;

public class NotFoundRedisValueException extends RuntimeException {

    public NotFoundRedisValueException(String key) {
        super(String.format("[%s] is not found", key));
    }

}
