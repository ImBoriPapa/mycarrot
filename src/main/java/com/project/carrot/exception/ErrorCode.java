package com.project.carrot.exception;

import lombok.Getter;

/**
 *
 */
@Getter
public enum ErrorCode {
    /**
     * 성공
     */
    SUCCESS("요청이 성공 되었습니다."),

    /**
     * Request 오류
     */

    //CreateMemberForm
    POST_EMPTY_CREATE_MEMBER_FORM("공백없이 입력해주세요");


    public String message;

    ErrorCode(String message) {
        this.message = message;
    }


}
