package com.project.carrot.domain.member.service;

import lombok.Getter;

@Getter
public enum MemberError {
    DUPLICATE_ID("중복된 아이디 입니다."),
    DUPLICATE_EMAIL("중복된 이메일입니다."),
    DUPLICATE_NICKNAME("중복된 닉네임입니다.");

    private String message;

    MemberError(String message) {
        this.message = message;
    }


}
