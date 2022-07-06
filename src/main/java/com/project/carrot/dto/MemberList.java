package com.project.carrot.dto;

import com.project.carrot.domain.entity.Member;

import java.time.LocalDateTime;

/**
 *  회원목록을 화면에 뿌려주는 DTO
 *
 */
public class MemberList {

    private Long memberId;
    private String userId;
    private String nickname;
    private LocalDateTime signUpDate;

    public MemberList() {}

    public MemberList(MemberListBuilder memberListBuilder) {
        this.memberId = memberListBuilder.memberId;
        this.userId = memberListBuilder.userId;
        this.nickname = memberListBuilder.nickname;
        this.signUpDate = memberListBuilder.signUpDate;
    }

    public  static class MemberListBuilder{
        private Long memberId;
        private String userId;
        private String nickname;
        private LocalDateTime signUpDate;

        public MemberListBuilder(Member member) {
            this.memberId = member.getMemberId();
            this.userId = member.getUserId();
            this.nickname = member.getNickname();
            this.signUpDate = member.getSignUpDate();
        }

        public MemberList builder(){
            return new MemberList(this);
        }
    }
}
