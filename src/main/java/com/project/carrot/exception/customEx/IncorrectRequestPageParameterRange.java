package com.project.carrot.exception.customEx;

import com.project.carrot.exception.BasicException;
import com.project.carrot.exception.errorCode.ErrorCode;
import org.springframework.validation.BindingResult;

import java.util.List;

public class IncorrectRequestPageParameterRange extends BasicException {
    @Override
    public String getErrorType() {
        return super.getErrorType();
    }

    @Override
    public int getErrorCode() {
        return super.getErrorCode();
    }

    @Override
    public List<String> getErrorMessage() {
        return super.getErrorMessage();
    }

    public IncorrectRequestPageParameterRange(ErrorCode code) {
        super(code);
    }

    public IncorrectRequestPageParameterRange(ErrorCode code, BindingResult bindingResult) {
        super(code, bindingResult);
    }
}
