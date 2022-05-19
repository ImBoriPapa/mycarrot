package com.project.carrot.entity;

import lombok.Getter;

import javax.persistence.*;

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
    private String member_nickname;

    public Member(){}
    public Member(Long id, String memberId, String memberPassword, String member_nickname) {
        this.id = id;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.member_nickname = member_nickname;
    }
}
