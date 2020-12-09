package rso.authorization.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        // 1. Create payload contains exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiExceptionDao apiExceptionDao = new ApiExceptionDao(
                e.getMessage(),
                badRequest,
                LocalDateTime.now()
        );

        // 2. Return response entity
        return new ResponseEntity<>(apiExceptionDao, badRequest);

    }
}
