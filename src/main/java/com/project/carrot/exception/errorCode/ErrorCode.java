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
    INCORRECT_REQUEST_PAGE_PARAMETER_RANGE("페이지 요청시 0이상 정수만 허용합니다.",1002),
    FAIL_TO_LOGIN("로그인에 실패했습니다 로그인 아이디 혹은 비밀번호를 확인해주세요.",1004),

    /**
     * 회원 가입시
     */
    DUPLICATE_LOGIN_ID("이미 사용중인 아이디 입니다.",2001),
    DUPLICATE_NICK_NAME("이미 사용중인 닉네임 입니다.",2002),
    DUPLICATE_EMAIL("이미 사용중인 이메일 입니다.",2003),

    /**
     * 회원 조회시
     */
    NO_EXIST_MEMBER("회원이 존재하지 않습니다.",1002),

    VALID_FAIL_ERROR("검증에 실패하였습니다.",1003),
    /**
     * password 오류
     */
    WRONG_PASSWORD("잘못된 비밀번호 입니다.",1001),

    NOT_MATCH_REFRESH_TOKEN("RefreshToken 이 일치하지 않습니다.",1001),

    /**
     * 접근 거절
     */
    ACCESS_DENIED("가입되지 않은 사용자의 접근입니다.",1001),

    /**
     * 주소값
     */
    INCORRECT_ADDRESS_CODE("Address code 는 1000~2000 사이 INT 입니다. ",1100);
    public String message;
    public int code;

    ErrorCode(String message, int code) {
        this.message = message;
        this.code = code;
    }

}
