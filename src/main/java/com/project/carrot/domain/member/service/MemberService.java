package com.project.carrot.domain.member.service;

import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 정보 저장
     * 회원가입시 한번 더 LoginId, email, nickName 중복 검사
     *
     */
    public Member saveMember(Member member) {
        if(validateDuplicateUserId(member.getLoginId())){
            throw new IllegalStateException("이미 사용중인 아이디 입니다.");
        }

        if(validateDuplicateEmail(member.getEmail())){
            throw new IllegalStateException("이미 사용중인 이메일 입니다.");
        }

        if (validateDuplicateNickname(member.getNickname())) {
            throw new IllegalStateException("이미 사용중인 닉네임 입니다.");
        }

        return memberRepository.save(member);
    }


    public boolean validateDuplicateUserId(String userId) {//회원아이디가 존재하면 true 없으면 false 반환
        return memberRepository.findByLoginId(userId).isPresent();
    }

    public boolean validateDuplicateEmail(String email){ //이메일이 존재하면 true 없으면 false
        return  memberRepository.findByEmail(email).isPresent();
    }

    public boolean validateDuplicateNickname(String nickname) {//닉네임이 존재하면 true 없으면 false
        return memberRepository.findByNickname(nickname).isPresent();
    }


    public Member checkIdAndPw(String userId, String password) { //아이디와 비밀번호로 존재하는 회원이면 Member 반환 아니면 null  반환

        return memberRepository.findByLoginId(userId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
        }

    public List<Member> members() { //회원 목록 조회
        return  memberRepository.findAll();
    }

}
