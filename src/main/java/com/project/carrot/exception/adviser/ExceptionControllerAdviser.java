package com.project.carrot.exception.adviser;

import com.project.carrot.exception.BasicException;
import com.project.carrot.exception.customEx.FailToLoginException;
import com.project.carrot.exception.customEx.IncorrectRequestPageParameterRange;
import com.project.carrot.exception.customEx.NoExistMemberException;
import com.project.carrot.exception.customEx.RequestValidationException;
import com.project.carrot.exception.member_exception.NoAddressException;
import com.project.carrot.utlis.response.CustomResponseStatus;
import com.project.carrot.utlis.response.ErrorResponse;
import com.project.carrot.utlis.response.ResponseForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdviser {

    @ExceptionHandler(IllegalArgumentException.class)
    public void illegal(IllegalArgumentException e) {
        log.error("ExceptionControllerAdviser is called exception = ", e);
    }

    @ExceptionHandler(NoAddressException.class)
    public void noAddressException(NoAddressException e) {
        log.error("ExceptionControllerAdviser is called exception = NoAddressException");
    }


    @ExceptionHandler(BasicException.class)
    public ResponseEntity basicEx(BasicException e) {
        log.info("BasicException call");

        ResponseForm responseForm = new ResponseForm(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL, new ErrorResponse(e));
        HttpHeaders headers = new HttpHeaders();

        return ResponseEntity.badRequest().headers(headers).body(responseForm);
    }

    @ExceptionHandler(NoExistMemberException.class)
    public ResponseEntity noExistMember(NoExistMemberException e) {
        log.info("NotExistMemberException call");

        ResponseForm responseForm = new ResponseForm(CustomResponseStatus.REQUEST_FIND_MEMBER_IS_FAIL, new ErrorResponse(e));
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(responseForm);
    }

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity validationError(RequestValidationException e) {
        log.info("RequestValidationException call");

        ResponseForm responseForm = new ResponseForm(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL, new ErrorResponse(e));
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.badRequest().headers(headers).body(responseForm);
    }

    @ExceptionHandler(IncorrectRequestPageParameterRange.class)
    public ResponseEntity validationError(IncorrectRequestPageParameterRange e) {
        log.info("IncorrectRequestPageParameterRange call");

        ResponseForm responseForm = new ResponseForm(CustomResponseStatus.REQUEST_ERROR, new ErrorResponse(e));
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.badRequest().headers(headers).body(responseForm);
    }

    @ExceptionHandler(FailToLoginException.class)
    public ResponseEntity loginError(FailToLoginException e){
        log.info("FailToLoginException call");

        ResponseForm responseForm = new ResponseForm(CustomResponseStatus.REQUEST_FAIL, new ErrorResponse(e));
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.badRequest().headers(headers).body(responseForm);
    }
}
