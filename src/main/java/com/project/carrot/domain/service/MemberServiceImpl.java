package com.project.carrot.domain.service;

import com.project.carrot.domain.entity.Member;
import com.project.carrot.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public boolean validationDuplicateUserId(String userId) {//회원아이디가 존재하면 true 없으면 false 반환
        Optional<Member> exitsId = memberRepository.findByUserId(userId);
        return exitsId.isPresent();
    }

    @Override
    public boolean validationDuplicateEmail(String email){ //이메일이 존재하면 true 없으면 false
        Optional<Member> exitsEmail = memberRepository.findByEmail(email);
        return exitsEmail.isPresent();
    }

    @Override
    public Member checkIdAndPw(String userId, String password) { //아이디와 비밀번호로 존재하는 회원이면 Member 반환 아니면 null  반환

        return memberRepository.findByUserId(userId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
        }

    @Override
    public Long saveMember(Member member) { //회원정보 저장, memberId 반환
        return memberRepository.save(member).getMemberId();
    }

    @Override
    public List<Member> members() { //회원 목록 조회
        return  memberRepository.findAll();
    }

}
