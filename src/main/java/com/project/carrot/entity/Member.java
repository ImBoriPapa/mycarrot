package com.project.carrot.entity;

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
    @Column(name="MODIFY_DATE")
    private LocalDateTime modifyDate;


    public Member() {}

    public Member(MemberBuilder memberBuilder) {
        this.id = memberBuilder.id;
        this.memberId = memberBuilder.memberId;
        this.memberPassword = memberBuilder.memberPassword;
        this.memberNickname = memberBuilder.memberNickname;
        this.memberEmail = memberEmail;
        this.signUpDate = signUpDate;
        this.modifyDate = modifyDate;
    }

   public static class MemberBuilder{
       private final Long id;
       private final String memberId;
       private final  String memberPassword;
       private final String memberNickname;
       private final String memberEmail;
       private final LocalDateTime signUpDate;
       private final LocalDateTime modifyDate;

       public MemberBuilder(MemberDTO memberDTO) {
           this.id = memberDTO.getId();
           this.memberId = memberDTO.getMemberEmail();
           this.memberPassword = memberDTO.getMemberPassword();
           this.memberNickname = memberDTO.getMemberNickname();
           this.memberEmail = memberDTO.getMemberEmail();
           this.signUpDate = LocalDateTime.now();
           this.modifyDate = LocalDateTime.now();
       }

       public Member builder(){
           return new Member(this);
       }


   }


}


