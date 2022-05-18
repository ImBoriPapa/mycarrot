package com.project.carrot.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name="member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String member_id;
    private String member_password;
    private String member_nickname;

    public Member(){}
    public Member(Long id, String member_id, String member_password, String member_nickname) {
        this.id = id;
        this.member_id = member_id;
        this.member_password = member_password;
        this.member_nickname = member_nickname;
    }
}
