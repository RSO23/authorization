package rso.authorization.exceptions;

public class ApiRequestException extends RuntimeException {
    private static final long serialVersionUID = -3633450916103693732L;

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
