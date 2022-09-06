package com.project.carrot.exception.member_exception;

public class NotMatchRefreshTokenException extends RuntimeException{
    public NotMatchRefreshTokenException(String message) {
        super(message);
    }
}
