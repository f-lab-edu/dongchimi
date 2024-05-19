package com.dcm.global.exception;

import com.dcm.chat.exception.NotFoundChatException;
import com.dcm.hobby.exception.NotFoundHobbyException;
import com.dcm.hobbydetail.exception.NotFoundHobbyDetailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({
        NotFoundChatException.class,
        NotFoundHobbyException.class,
        NotFoundHobbyDetailException.class
    })
    public ResponseEntity<ErrorResponse> handleNotfoundException(RuntimeException exception) {
        String message = exception.getMessage();
        log.warn(message, exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(message));
    }


}
