package com.project.carrot.domain.member;

import com.project.carrot.domain.member.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

@Getter
public class MemberAccount extends User {

    private Member member;

    public MemberAccount(Member member) {
        super(member.getLoginId(), member.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.member = member;
    }
}
