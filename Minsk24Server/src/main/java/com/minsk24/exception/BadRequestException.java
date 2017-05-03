package com.minsk24.exception;

/**
 * Created by khadunai on 4/19/2017.
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super();
    }
    public BadRequestException(String message) {
        super(message);
    }
}
