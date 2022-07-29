package com.project.carrot.web.controller.member;

import com.project.carrot.domain.member.entity.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 회원 가입시 회원정보 DTO
 * 가입정보 validate
 */

public class MemberDto {

    private Long memberId;
    @NotNull
    @NotBlank
    private String loginId;
    @NotNull
    private String password;
    private String nickname;
    private String email;
    private LocalDateTime signUpDate;
    private LocalDateTime modifyDate;

    public MemberDto() {}

    public MemberDto(Member member){
        this.memberId = member.getMemberId();
        this.loginId = member.getLoginId();
        this.password = member.getPassword();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.signUpDate = member.getSignUpDate();
        this.modifyDate = member.getModifyDate();

    }

    public MemberDto(Long memberId, String loginId, String password, String nickname, String email, LocalDateTime signUpDate, LocalDateTime modifyDate) {
        this.memberId = memberId;
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.signUpDate = signUpDate;
        this.modifyDate = modifyDate;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getUserId() {
        return loginId;
    }

    public void setUserId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
