package com.project.carrot.exception.member_exception;

import lombok.Getter;

@Getter
public enum MemberError {
    /**
     * 로그인 아이디, 이메일, 닉네임 중복
     */
    DUPLICATE_ID("중복된 아이디 입니다."),
    DUPLICATE_EMAIL("중복된 이메일입니다."),
    DUPLICATE_NICKNAME("중복된 닉네임입니다."),

    /**
     * 로그인 아이디,이메일,닉네임이 공백,또는 null
     */
    BLANK_OR_NULL_ID("유효한 아이디가 아닙니다."),
    BLANK_OR_NULL_EMAIL("유효한 아이디가 아닙니다."),
    BLANK_OR_NULL_NICKNAME("유효한 아이디가 아닙니다.");

    private String message;

    MemberError(String message) {
        this.message = message;
    }
}
