package com.project.carrot.domain.member.service;


import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.entity.MemberRoll;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.exception.member_exception.MemberError;
import com.project.carrot.exception.member_exception.MemberServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

   //회원 정보 저장
    public Long saveMember(Member member) {

        validateBlank(member);

        validateSaveInfoWhenSaveMember(member);

        member.createMember(member, MemberRoll.USER, LocalDateTime.now(),member.getAddress().get(0));
        return memberRepository.save(member).getMemberId();
    }
    //로그인 아이디,이메일,닉네임이 공백,또는 null
    private void validateBlank(Member member) {
        if (!StringUtils.hasText(member.getLoginId())) {
            throw new MemberServiceException(MemberError.BLANK_OR_NULL_ID);
        }

        if (!StringUtils.hasText(member.getEmail())) {
            throw new MemberServiceException(MemberError.BLANK_OR_NULL_EMAIL);
        }

        if (!StringUtils.hasText(member.getNickname())) {
            throw new MemberServiceException(MemberError.BLANK_OR_NULL_NICKNAME);
        }
    }
    //로그인 아이디, 이메일, 닉네임이 중복일 경우
    private void validateSaveInfoWhenSaveMember(Member member) {
        if(validateDuplicateUserId(member.getLoginId())){
            throw new MemberServiceException(MemberError.DUPLICATE_ID);
        }

        if (validateDuplicateEmail(member.getEmail())) {
            throw new MemberServiceException(MemberError.DUPLICATE_EMAIL);
        }

        if (validateDuplicateNickname(member.getNickname())) {
            throw new MemberServiceException(MemberError.DUPLICATE_NICKNAME);
        }
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
