package com.project.carrot.domain.member.userDetailsService;

import com.project.carrot.domain.member.entity.Member;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class MemberAccount extends User {

    private Member member;
    private SecurityMemberInformation information;

    public MemberAccount(Member member) {
        super(member.getMemberId().toString(), member.getPassword(), List.of(new SimpleGrantedAuthority(member.getMemberRoll().toString())));
        this.member = member;
    }

    public MemberAccount(SecurityMemberInformation information) {
        super(information.getMemberId(), information.getPassword(), List.of(new SimpleGrantedAuthority("ROLL_USER")));
        this.information = information;
    }
}
