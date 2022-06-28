package com.project.carrot.domain.entity;

import com.project.carrot.dto.MemberDTO;
import lombok.Getter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

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

    @Column(name="MODIFY_DATE")
    private LocalDateTime modifyDate;

    public Member() {}

    private Member(MemberBuilder memberBuilder) {
        this.id = memberBuilder.id;
        this.memberId = memberBuilder.memberId;
        this.memberPassword =memberBuilder.memberPassword;
        this.memberNickname = memberBuilder.memberNickname;
        this.memberEmail = memberBuilder.memberEmail;
        this.signUpDate = memberBuilder.signUpDate;
        this.modifyDate = memberBuilder.modifyDate;
    }

    //MemberDTO - > Member
   public static class MemberBuilder{
       private  Long id;
       private  String memberId;
       private  String memberPassword;
       private  String memberNickname;
       private  String memberEmail;
       private  LocalDateTime signUpDate;
       private  LocalDateTime modifyDate;

       public MemberBuilder(MemberDTO memberDTO) {
           this.id = memberDTO.getId();
           this.memberId = memberDTO.getMemberId();
           this.memberPassword = memberDTO.getMemberPassword();
           this.memberNickname = memberDTO.getMemberNickname();
           this.memberEmail = memberDTO.getMemberEmail();
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


