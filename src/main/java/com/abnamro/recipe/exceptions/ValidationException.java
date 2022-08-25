package com.abnamro.recipe.exceptions;

import org.springframework.http.HttpStatus;

public class ValidationException extends RuntimeException implements ICustomException{

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public ValidationException() {
        super();
    }

    public ValidationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    protected ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
