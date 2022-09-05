package com.project.carrot.exception.adviser;

import com.project.carrot.exception.member_exception.NoAddressException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdviser {

    @ExceptionHandler(IllegalArgumentException.class)
    public void illegal(IllegalArgumentException e){
        log.error("ExceptionControllerAdviser is called exception = IllegalArgumentException");
    }

    @ExceptionHandler(NoAddressException.class)
    public void noAddressException(NoAddressException e){
        log.error("ExceptionControllerAdviser is called exception = NoAddressException");
    }

}
