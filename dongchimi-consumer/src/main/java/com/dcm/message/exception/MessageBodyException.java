package com.dcm.message.exception;

public class MessageBodyException extends RuntimeException {

    public MessageBodyException() {
        super("메세지 본문에 문제가 발생했습니다.");
    }


}
