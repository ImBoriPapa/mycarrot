package com.project.carrot.dto;

import java.time.LocalDateTime;

public class MemberList {

    private Long memberId;
    private String userId;
    private String nickname;
    private LocalDateTime signUpDate;

    public MemberList() {}

    public MemberList(Long memberId, String userId, String nickname, LocalDateTime signUpDate) {
        this.memberId = memberId;
        this.userId = userId;
        this.nickname = nickname;
        this.signUpDate = signUpDate;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(LocalDateTime signUpDate) {
        this.signUpDate = signUpDate;
    }
}
