package com.project.carrot.entity;

import lombok.Getter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter

@Table(name="MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="MEMBER_ID")
    private String memberId;
    @Column(name="MEMBER_PASSWORD")
    private String memberPassword;
    @Column(name = "MEMBER_NICKNAME")
    private String memberNickname;
    @Column(name = "MEMBER_EMAIL")
    private String memberEmail;
    @Column(name="SIGN_UP_DATE")
    private LocalDateTime signUpDate;


    public Member(){}

    public Member(Long id, String memberId, String memberPassword, String memberNickname, String memberEmail, LocalDateTime signUpDate) {
        this.id = id;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberNickname = memberNickname;
        this.memberEmail = memberEmail;
        this.signUpDate = signUpDate;
    }

    public Member(String memberId, String memberPassword, String memberNickname, String memberEmail, LocalDateTime signUpDate) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberNickname = memberNickname;
        this.memberEmail = memberEmail;
        this.signUpDate = signUpDate;
    }
}


