package com.project.carrot.exception.member_exception;

import lombok.Getter;

@Getter
public class NotExistMemberException extends RuntimeException{
    private MemberError MemberError;
    public NotExistMemberException(MemberError MemberError) {
        this.MemberError = MemberError;
    }
}
