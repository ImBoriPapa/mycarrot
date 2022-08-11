package com.project.carrot.domain.member.entity;

import com.project.carrot.domain.address.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "MEMBER")
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ROLL")
    @Enumerated(value = EnumType.STRING)
    private MemberRoll memberRoll;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)//일대다 관계 주소를 1개 혹은 2개를 저장하고 수정이 가능
    @JoinColumn(name = "MEMBER_ID")
    private List<Address> address = new ArrayList();

    @Column(name = "SIGN_UP_DATE") //회원 등록일
    private LocalDateTime signUpDate;

    @Column(name = "MODIFY_DATE") //회원정보 수정일
    private LocalDateTime modifyDate;

    public Member() {
    }

    /**
     * 최초 가입시
     * signUpDate 설정
     * memberRoll - USER
     */
    public void createMember(Member member) {
        this.loginId = member.getLoginId();
        this.password = member.getPassword();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.memberRoll = MemberRoll.USER;
        this.address = member.getAddress();
        this.signUpDate = LocalDateTime.now();
    }


}


