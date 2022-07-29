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

   //회원 정보 저장
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }
    //회원아이디가 존재하면 true 없으면 false 반환

    public boolean validateDuplicateUserId(String userId) {
        return memberRepository.findByLoginId(userId).isPresent();
    }
    //이메일이 존재하면 true 없으면 false
    public boolean validateDuplicateEmail(String email){
        return  memberRepository.findByEmail(email).isPresent();
    }
    //닉네임이 존재하면 true 없으면 false
    public boolean validateDuplicateNickname(String nickname) {
        return memberRepository.findByNickname(nickname).isPresent();
    }

    //아이디와 비밀번호로 존재하는 회원이면 Member 반환 아니면 null  반환
    public Member checkIdAndPw(String userId, String password) {

        return memberRepository.findByLoginId(userId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
        }

    public List<Member> members() { //회원 목록 조회
        return  memberRepository.findAll();
    }

}
