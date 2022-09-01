package com.project.carrot.exception.member_exception;

/**
 *  Address 가 없을시
 */
public class NoAddressException extends RuntimeException {

    public NoAddressException(String message) {
        super(message);
    }
}
