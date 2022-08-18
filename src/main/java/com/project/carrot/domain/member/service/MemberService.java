package com.project.carrot.domain.member.service;


import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.entity.MemberRoll;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.exception.member_exception.MemberError;
import com.project.carrot.exception.member_exception.MemberServiceException;

import com.project.carrot.web.controller.member.dto.CreateMemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    //회원 정보 저장
    public Long saveMember(CreateMemberForm createMemberForm) {

        //회원정보 저장
        Member member = Member.createMember(
                createMemberForm.getLoginId(),
                createMemberForm.getPassword(),
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


    //아이디와 비밀번호로 존재하는 회원이면 Member 반환 아니면 null  반환
    public Member checkIdAndPw(String userId, String password) {

        return memberRepository.findByLoginId(userId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }


}
