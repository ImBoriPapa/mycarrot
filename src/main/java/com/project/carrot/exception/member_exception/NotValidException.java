package com.project.carrot.exception.member_exception;

import com.project.carrot.exception.errorCode.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotValidException extends RuntimeException {
    private ErrorCode code;
}
