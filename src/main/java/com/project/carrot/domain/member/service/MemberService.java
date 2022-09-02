package com.project.carrot.domain.member.service;


import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.entity.MemberRoll;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.exception.member_exception.MemberError;
import com.project.carrot.exception.member_exception.NotExistMemberException;
import com.project.carrot.web.controller.member.ImagePath;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 정보 저장 기능
     * password encoding 후 저장
     * @param member
     * @param fullAddress
     * @return
     */
    public Long saveMember(Member member,String fullAddress) {
        String encodedPassword = passwordEncoder.encode(member.getPassword());

        Member createdMember = Member.createMember(member,encodedPassword, ImagePath.DEFAULT_PROFILE_IMAGE,MemberRoll.R0LL_USER,fullAddress);

        return memberRepository.save(createdMember).getMemberId();
    }

    public void updateMember(Long id,Member updateParam) {
        Optional<Member> findMember = memberRepository.findByMemberId(id);
        findMember.orElseThrow(()->new IllegalArgumentException("회원정보가 없습니다.")).modifiedMember(updateParam);
    }

    //회원 전체 목록 조회
    public List<Member> findMemberList() {
        return memberRepository.findAll();
    }

    //회원 목록 아이디로 조회
    public Member findMember(Long id) {
        return memberRepository.findByMemberId(id).orElseThrow(() -> new NotExistMemberException(MemberError.NOT_EXIST_MEMBER));
    }


}
