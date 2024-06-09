package com.dcm.global.exception;

import com.dcm.hobby.exception.NotFoundHobbyException;
import com.dcm.hobbydetail.exception.NotFoundHobbyDetailException;
import com.dcm.job.exception.NotFoundJobException;
import com.dcm.party.exception.ExistPartyRequestException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Optional<FieldError> fieldErrorOptional = exception.getFieldErrors().stream().findFirst();

        if (fieldErrorOptional.isPresent()) {
            FieldError fieldError = fieldErrorOptional.get();
            String message = String.format("%s은(는) %s (요청 값: %s)", fieldError.getField(),
                    fieldError.getDefaultMessage(), fieldError.getRejectedValue());
            log.warn(message);

            return ResponseEntity.badRequest()
                    .body(new ErrorResponse(message));
        }

        return ResponseEntity.badRequest()
                .body(new ErrorResponse("알 수 없는 요청 값입니다."));
    }

    @ExceptionHandler({
        NotFoundJobException.class,
        NotFoundHobbyException.class,
        NotFoundHobbyDetailException.class
    })
    public ResponseEntity<ErrorResponse> handleNotfoundException(RuntimeException exception) {
        String message = exception.getMessage();
        log.warn(message, exception);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(message));
    }

    @ExceptionHandler(ExistPartyRequestException.class)
    public ResponseEntity<ErrorResponse> handleExistTargetException(RuntimeException exception) {
        String message = exception.getMessage();
        log.warn(message, exception);

        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new ErrorResponse(message));
    }


}
