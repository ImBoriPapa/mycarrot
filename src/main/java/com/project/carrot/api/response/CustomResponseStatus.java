package com.project.carrot.api.response;

import lombok.Getter;

@Getter
public enum CustomResponseStatus {

    /**
     * Request success
     */
    SUCCESS("요청이 정상적으로 처리 되었습니다."),
    SIGNUP_REQUEST_IS_SUCCESS("회원 가입 요청이 성공하였습니다."),

    /**
     * Request Fail
     */
    REQUEST_ERROR("비정상적인 요청입니다."),
    REQUEST_FAIL("요청이 실패하였습니다."),
    SIGNUP_REQUEST_IS_FAIL("회원 가입 요청이 실패하였습니다.");

    private String message;

    CustomResponseStatus(String message) {
        this.message = message;
    }
}