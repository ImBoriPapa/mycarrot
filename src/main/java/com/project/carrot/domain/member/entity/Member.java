package com.project.carrot.domain.member.entity;

import lombok.Getter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private  Long memberId;

    @Column(name="LOGIN_ID")
    private String loginId;

    @Column(name="password")
    private String password;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ROLL")
    private MemberRoll memberRoll;

    @Column(name="SIGN_UP_DATE") //회원 등록일
    private LocalDateTime signUpDate;

    @Column(name="MODIFY_DATE") //회원정보 수정일
    private LocalDateTime modifyDate;

    public Member() {}

    private Member(MemberBuilder memberBuilder) {
        this.memberId = memberBuilder.memberId;
        this.loginId = memberBuilder.loginId;
        this.password =memberBuilder.password;
        this.nickname = memberBuilder.nickname;
        this.email = memberBuilder.email;
        this.memberRoll = memberBuilder.memberRoll;
        this.signUpDate = memberBuilder.signUpDate;
        this.modifyDate = memberBuilder.modifyDate;
    }

    /**
     * MemberBuilder: Member 를 생성하고 setter 를 사용하지 않고  값을 세팅하기 위해 설정
     * 현재는 필수값 선택값 구분 없이 구현
     * 후에 비즈니즈 로직이 들어갈 경우 리펙토링
     */

   public static class MemberBuilder{
       private  Long memberId;
       private  String loginId;
       private  String password;
       private  String nickname;
       private  String email;
       private MemberRoll memberRoll;
       private  LocalDateTime signUpDate;
       private  LocalDateTime modifyDate;

       public MemberBuilder() {}

       public  MemberBuilder memberId(Long memberId){
           this.memberId = memberId;
           return this;
       }

       public MemberBuilder loginId(String loginId){
           this.loginId = loginId;
           return this;
       }

       public MemberBuilder password(String password){
           this.password = password;
           return this;
       }

       public MemberBuilder nickname(String nickname){
           this.nickname = nickname;
           return this;
       }

       public MemberBuilder email(String email){
           this.email = email;
           return this;
       }

        public MemberBuilder memberRoll(MemberRoll memberRoll) {
            this.memberRoll = memberRoll;
            return this;
        }

       public MemberBuilder signUpdate(LocalDateTime signUpdate){
           this.signUpDate = signUpdate;
           return this;
       }



       public MemberBuilder modifyDate(LocalDateTime modifyDate){
           this.modifyDate = modifyDate;
           return this;
       }

       public Member builder(){
           return new Member(this);
       }


   }


}


