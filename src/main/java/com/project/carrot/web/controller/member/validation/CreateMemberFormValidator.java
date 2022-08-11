package com.project.carrot.web.controller.member.validation;

import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.web.controller.member.dto.CreateMemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CreateMemberFormValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(CreateMemberForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateMemberForm createMemberForm = (CreateMemberForm) target;

        if(memberRepository.existsByLoginId(createMemberForm.getLoginId())){
            errors.rejectValue(
                    "loginId","invalid.loginId",
                    new Object[]{createMemberForm.getLoginId()},
                    "이미 사용중인 아이디입니다.");
        }

        if(memberRepository.existsByNickname(createMemberForm.getNickname())){
            errors.rejectValue(
                    "nickname","invalid.nickname",
                    new Object[]{createMemberForm.getNickname()},
                    "이미 사용중인 닉네입입니다.");
        }

        if(memberRepository.existsByEmail(createMemberForm.getLoginId())){
            errors.rejectValue(
                    "email","invalid.email",
                    new Object[]{createMemberForm.getEmail()},
                    "이미 사용중인 이메일입니다.");
        }
    }
}
