package com.project.carrot.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDTO {

    private Long id;
    private String memberId;
    private String memberPassword;
    private String memberNickname;
    private String memberEmail;
    private LocalDateTime signUpDate;

    public MemberDTO(){};

    public MemberDTO(Long id, String memberId, String memberPassword, String memberNickname, String memberEmail, LocalDateTime signUpDate) {
        this.id = id;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberNickname = memberNickname;
        this.memberEmail = memberEmail;
        this.signUpDate = signUpDate;
    }
}
