package com.project.carrot.exception.errorCode;

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
    NO_RESULT_FIND_MEMBER("요청하신 정보로 로그인 정보를 조회하는대 실패하였습니다."),
    //CreateMemberForm
    POST_EMPTY_CREATE_MEMBER_FORM("공백없이 입력해주세요"),
    /**
     * password 오류
     */
    WRONG_PASSWORD("잘못된 비밀번호 입니다."),

    NOT_MATCH_REFRESH_TOKEN("RefreshToken 이 일치하지 않습니다."),

    /**
     * 접근 거절
     */
    ACCESS_DENIED("가입되지 않은 사용자의 접근입니다.");

    public String message;

    ErrorCode(String message) {
        this.message = message;
    }


}
