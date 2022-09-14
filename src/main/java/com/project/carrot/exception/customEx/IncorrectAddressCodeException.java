package com.project.carrot.exception.customEx;

import com.project.carrot.exception.BasicException;
import com.project.carrot.exception.errorCode.ErrorCode;
import org.springframework.validation.BindingResult;

import java.util.List;

public class IncorrectAddressCodeException extends BasicException {
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

    public IncorrectAddressCodeException(ErrorCode code) {
        super(code);
    }

    public IncorrectAddressCodeException(ErrorCode code, BindingResult bindingResult) {
        super(code, bindingResult);
    }
}
