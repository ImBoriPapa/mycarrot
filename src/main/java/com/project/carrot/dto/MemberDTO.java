package com.project.carrot.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


public class MemberDTO {

    private Long memberId;
    @NotNull
    @NotBlank
    private String userId;
    @NotNull
    private String password;
    private String nickname;
    private String email;
    private LocalDateTime signUpDate;
    private LocalDateTime modifyDate;

    public MemberDTO() {}

    public MemberDTO(Long memberId, String userId, String password, String nickname, String email, LocalDateTime signUpDate, LocalDateTime modifyDate) {
        this.memberId = memberId;
        this.userId = userId;
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
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
