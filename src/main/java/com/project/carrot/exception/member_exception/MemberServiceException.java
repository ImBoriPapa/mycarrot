package com.project.carrot.exception.member_exception;

import lombok.Getter;

@Getter
public class MemberServiceException extends RuntimeException{
    private MemberError MemberError;


    public MemberServiceException(MemberError MemberError) {
        this.MemberError = MemberError;
    }
}
