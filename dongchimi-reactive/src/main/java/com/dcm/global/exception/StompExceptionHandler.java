package com.dcm.global.exception;

import com.dcm.global.stomp.StompPrincipal;
import com.dcm.message.dto.MessageRequest;
import com.dcm.message.exception.MessagePublishException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class StompExceptionHandler {

    private final SimpMessageSendingOperations messageTemplate;

    @MessageExceptionHandler(MessagePublishException.class)
    public void handleMessagePublishException(StompPrincipal principal, MessagePublishException exception, @Payload MessageRequest request) {
        log.warn(exception.getMessage(), exception);
        ErrorMessage<MessageRequest> errorMessage = new ErrorMessage<>(exception.getMessage(), request);
        messageTemplate.convertAndSendToUser(principal.getName(), "/error", errorMessage);
    }

}
