package com.dcm.global.exception;

import com.dcm.message.dto.MessageRequest;
import com.dcm.message.exception.MessagePublishException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.security.Principal;

/**
 * ControllerAdvice의 annotation은 @ControllerAdvice @RestControllerAdvice 두가지가 있습니다. 예외 발생 시 json형태로 결과를 반환하려면
 * @RestControllerAdvice를 클래스에 선언하면 됩니다. 어노테이션에 추가로 패키지를 적용하면 위에서 설명한 것처럼 특정 패키지 하위의 Controller에만 로직이 적용되게도 할 수 있습니다.
 * ex) @RestControllerAdvice(basePackages = “com.dcm.message”)
 * 아무것도 적용하지 않으면 프로젝트의 모든 Controller에 로직이 적용됩니다.
 * 여기서는 STOMP 통신의 예외처리를 위해 @ControllerAdvice로 선언하여 @MessageExceptionHandler를 통해 STOMP 통신이 해제되지 않고,
 * 에러 응답을 전달 할 수 있도록 예외를 처리합니다.
 */
@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class StompExceptionHandler {

    private final SimpMessageSendingOperations messageTemplate;

    @MessageExceptionHandler(MessagePublishException.class)
    public void handleMessagePublishException(Principal principal, MessagePublishException exception, @Payload MessageRequest request) {
        log.warn(exception.getMessage(), exception);
        ErrorMessage<MessageRequest> errorMessage = new ErrorMessage<>(exception.getMessage(), request);
        messageTemplate.convertAndSendToUser(principal.getName(), "/error", errorMessage);
    }

}
