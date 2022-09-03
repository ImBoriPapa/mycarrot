package com.project.carrot.api.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomResponseStatus {

    /**
     * 2000~
     */
    SUCCESS(HttpStatus.OK.value(),HttpStatus.OK,2000,"요청이 정상적으로 처리 되었습니다."),

    /**
     * 4000
     */
    REQUEST_ERROR(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,4000,"비정상적인 요청입니다.");

    private Integer httpStatusCode;
    private HttpStatus httpStatus;
    private Integer customCode;
    private String message;

    CustomResponseStatus(Integer httpStatusCode, HttpStatus httpStatus, Integer customCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.customCode = customCode;
        this.message = message;
    }
}