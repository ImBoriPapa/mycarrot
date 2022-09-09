package com.project.carrot.exception.errorCode;

import lombok.Getter;

@Getter
public enum ErrorCode {

    /**
     * Request 오류
     */
    NO_RESULT_FIND_MEMBER("요청하신 정보로 로그인 정보를 조회하는대 실패하였습니다.",1000),
    //CreateMemberForm
    POST_EMPTY_CREATE_MEMBER_FORM("공백없이 입력해주세요",1001),

    VALID_FAIL_ERROR("검증에 실패하였습니다.",1002),
    /**
     * password 오류
     */
    WRONG_PASSWORD("잘못된 비밀번호 입니다.",1001),

    NOT_MATCH_REFRESH_TOKEN("RefreshToken 이 일치하지 않습니다.",1001),

    /**
     * 접근 거절
     */
    ACCESS_DENIED("가입되지 않은 사용자의 접근입니다.",1001);

    public String message;
    public int code;

    ErrorCode(String message, int code) {
        this.message = message;
        this.code = code;
    }

}
