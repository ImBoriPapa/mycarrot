package com.project.carrot.api.response;

import com.project.carrot.exception.BasicException;
import lombok.Getter;

import java.util.List;

@Getter
public class ErrorResponse {

    private String errorType;
    private int errorCode;
    private List<String> errorMessage;

    public ErrorResponse(BasicException exception) {
        this.errorType = exception.getErrorType();
        this.errorCode = exception.getErrorCode();
        this.errorMessage = exception.getErrorMessage();
    }
}
