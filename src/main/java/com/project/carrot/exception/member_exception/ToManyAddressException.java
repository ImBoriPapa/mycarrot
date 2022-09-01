package com.project.carrot.exception.member_exception;

public class ToManyAddressException extends RuntimeException{
    public ToManyAddressException(String message) {
        super(message);
    }
}
