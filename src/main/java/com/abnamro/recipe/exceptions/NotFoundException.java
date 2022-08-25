package com.abnamro.recipe.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException implements ICustomException {

    private HttpStatus status = HttpStatus.NOT_FOUND;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    protected NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
