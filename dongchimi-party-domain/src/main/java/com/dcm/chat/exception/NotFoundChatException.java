package com.dcm.chat.exception;

public class NotFoundChatException extends RuntimeException {

    public NotFoundChatException(Long chatId) {
        super(String.format("[Chat ID: %s] chat is not found", chatId));
    }
}
