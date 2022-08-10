package com.project.carrot.validation;

import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.exception.member_exception.MemberError;
import com.project.carrot.exception.member_exception.MemberServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
@Component
@RequiredArgsConstructor
public class MemberServiceValidation {

    private final MemberRepository memberRepository;

    //로그인 아이디,이메일,닉네임이 공백,또는 null
    public void validateBlank(Member member) {
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
    //회원가입시 로그인 아이디, 이메일, 닉네임이 중복이거나 주소값이 없을 경우
    public void validateSaveInfoWhenSaveMember(Member member) {
        if(validateDuplicateUserId(member.getLoginId())){
            throw new MemberServiceException(MemberError.DUPLICATE_ID);
        }

        if (validateDuplicateEmail(member.getEmail())) {
            throw new MemberServiceException(MemberError.DUPLICATE_EMAIL);
        }

        if (validateDuplicateNickname(member.getNickname())) {
            throw new MemberServiceException(MemberError.DUPLICATE_NICKNAME);
        }

        if (member.getAddress().isEmpty()) {
            throw new MemberServiceException(MemberError.NOT_EXIST_ADDRESS);
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
}
