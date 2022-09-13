package com.project.carrot.exception.adviser;

import com.project.carrot.utlis.response.CustomResponseStatus;
import com.project.carrot.utlis.response.ErrorResponse;
import com.project.carrot.utlis.response.ResponseForm;
import com.project.carrot.exception.BasicException;
import com.project.carrot.exception.member_exception.NoAddressException;
import com.project.carrot.exception.customEx.NotExistMemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
        log.info("Exception Message = {}",e.getMessage());

        ResponseForm responseForm = new ResponseForm(CustomResponseStatus.SIGNUP_REQUEST_IS_FAIL, new ErrorResponse(e));
        HttpHeaders headers = new HttpHeaders();

        return ResponseEntity.badRequest().headers(headers).body(responseForm);
    }

    @ExceptionHandler(NotExistMemberException.class)
    public ResponseEntity NoExistMember(NotExistMemberException e) {
        log.info("NotExistMemberException call");

        ResponseForm responseForm = new ResponseForm(CustomResponseStatus.REQUEST_FIND_MEMBER_IS_FAIL, new ErrorResponse(e));
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.badRequest().headers(headers).body(responseForm);
    }
}
