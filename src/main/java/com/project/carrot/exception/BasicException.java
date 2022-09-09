package com.project.carrot.exception;

import com.project.carrot.exception.errorCode.ErrorCode;
import lombok.Getter;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BasicException extends RuntimeException{
    private String errorType;
    private int errorCode;
    private List<String> errorMessage;

    public BasicException(ErrorCode code) {
        this.errorType = this.getClass().getName();
        this.errorCode = code.code;
        this.errorMessage = List.of(code.message);
    }

    public BasicException(ErrorCode code, BindingResult bindingResult) {
        List<String> errorMessage = getErrorList(bindingResult);
        this.errorType = this.getClass().getName();
        this.errorCode = code.code;
        this.errorMessage =  errorMessage;
    }

    private static List<String> getErrorList(BindingResult bindingResult) {
        List<String> errorMessage = bindingResult.getFieldErrors().stream().map(MessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return errorMessage;
    }
}
