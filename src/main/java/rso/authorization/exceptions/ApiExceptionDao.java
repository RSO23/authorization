package rso.authorization.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiExceptionDao {
    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime timestamp;

}

