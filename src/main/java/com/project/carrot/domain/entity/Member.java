package com.project.carrot.domain.entity;

import com.project.carrot.dto.MemberDto;
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

    @Column(name="USER_ID")
    private String userId;

    @Column(name="password")
    private String password;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "EMAIL")
    private String email;

    @Column(name="SIGN_UP_DATE")
    private LocalDateTime signUpDate;

    @Column(name="MODIFY_DATE")
    private LocalDateTime modifyDate;

    public Member() {}

    private Member(MemberBuilder memberBuilder) {
        this.memberId = memberBuilder.memberId;
        this.userId = memberBuilder.userId;
        this.password =memberBuilder.password;
        this.nickname = memberBuilder.nickname;
        this.email = memberBuilder.email;
        this.signUpDate = memberBuilder.signUpDate;
        this.modifyDate = memberBuilder.modifyDate;
    }

    //MemberDTO - > Member
   public static class MemberBuilder{
       private  Long memberId;
       private  String userId;
       private  String password;
       private  String nickname;
       private  String email;
       private  LocalDateTime signUpDate;
       private  LocalDateTime modifyDate;

       public MemberBuilder(MemberDto memberDTO) {
           this.memberId = memberDTO.getMemberId();
           this.userId = memberDTO.getUserId();
           this.password = memberDTO.getPassword();
           this.nickname = memberDTO.getNickname();
           this.email = memberDTO.getEmail();
       }

       public MemberBuilder signUpdate(LocalDateTime date){
           signUpDate = date;
           return this;
       }

       public MemberBuilder modifyDate(LocalDateTime date){
           modifyDate = date;
           return this;
       }

       public Member builder(){
           return new Member(this);
       }


   }


}


