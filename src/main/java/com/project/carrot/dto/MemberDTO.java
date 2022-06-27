package com.project.carrot.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


public class MemberDTO {

    private Long id;
    private String memberId;
    private String memberPassword;
    private String memberNickname;
    private String memberEmail;
    private LocalDateTime signUpDate;
    private LocalDateTime modifyDate;

    public MemberDTO() {}

    public MemberDTO(Long id, String memberId, String memberPassword, String memberNickname, String memberEmail, LocalDateTime signUpDate, LocalDateTime modifyDate) {
        this.id = id;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberNickname = memberNickname;
        this.memberEmail = memberEmail;
        this.signUpDate = signUpDate;
        this.modifyDate = modifyDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public LocalDateTime getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(LocalDateTime signUpDate) {
        this.signUpDate = signUpDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }
}
