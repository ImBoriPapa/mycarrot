package com.project.carrot.domain.member.service;


import com.project.carrot.domain.member.MemberAccount;
import com.project.carrot.domain.member.entity.Member;

import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.exception.member_exception.MemberError;
import com.project.carrot.exception.member_exception.MemberServiceException;

import com.project.carrot.web.controller.member.dto.CreateMemberForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

import java.util.*;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    //회원 정보 저장
    public Long saveMember(CreateMemberForm createMemberForm) {

        //회원정보 저장
        Member member = Member.createMember(
                createMemberForm.getLoginId(),
                passwordEncoder.encode(createMemberForm.getPassword()),
                createMemberForm.getNickname(),
                createMemberForm.getEmail(),
                createMemberForm.getAddress());

        return memberRepository.save(member).getMemberId();
    }

    //회원 전체 목록 조회
    public List<Member> findMemberList() {
        return memberRepository.findAll();
    }

    //회원 목록 아이디로 조회
    public Member findMember(Long id) {
        return memberRepository.findByMemberId(id).orElseThrow(() -> new MemberServiceException(MemberError.NOT_EXIST_MEMBER));
    }


    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        Optional<Member> findMember = memberRepository.findByLoginId(nickname);
        log.info("findMember ={}",findMember);

        if (findMember == null) {
            throw new UsernameNotFoundException(nickname);
        }

        return new MemberAccount(findMember.get());
    }
}
