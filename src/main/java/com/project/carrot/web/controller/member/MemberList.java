package com.project.carrot.web.controller.member;

import com.project.carrot.domain.member.entity.Member;

import java.time.LocalDateTime;

/**
 *  회원목록을 화면에 뿌려주는 DTO
 *
 */
public class MemberList {

    private Long memberId;
    private String loginId;
    private String nickname;
    private LocalDateTime signUpDate;

    public MemberList() {}

    public MemberList(MemberListBuilder memberListBuilder) {
        this.memberId = memberListBuilder.memberId;
        this.loginId = memberListBuilder.loginId;
        this.nickname = memberListBuilder.nickname;
        this.signUpDate = memberListBuilder.signUpDate;
    }

    public  static class MemberListBuilder{
        private Long memberId;
        private String loginId;
        private String nickname;
        private LocalDateTime signUpDate;

        public MemberListBuilder(Member member) {
            this.memberId = member.getMemberId();
            this.loginId = member.getLoginId();
            this.nickname = member.getNickname();
            this.signUpDate = member.getSignUpDate();
        }

        public MemberList builder(){
            return new MemberList(this);
        }
    }
}
