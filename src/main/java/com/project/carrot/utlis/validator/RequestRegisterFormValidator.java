package com.project.carrot.utlis.validator;

import com.project.carrot.api.member.form.request.RequestRegisterForm;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.exception.customEx.RequestValidationException;
import com.project.carrot.exception.errorCode.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class RequestRegisterFormValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestRegisterForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RequestRegisterForm requestRegisterForm = (RequestRegisterForm) target;

        if (memberRepository.existsByLoginId(requestRegisterForm.getLoginId())) {
            throw new RequestValidationException(ErrorCode.DUPLICATE_LOGIN_ID);
        }

        if (memberRepository.existsByNickname(requestRegisterForm.getNickname())) {
            throw new RequestValidationException(ErrorCode.DUPLICATE_NICK_NAME);
        }

        if (memberRepository.existsByEmail(requestRegisterForm.getEmail())) {
            throw new RequestValidationException(ErrorCode.DUPLICATE_EMAIL);
        }
    }
}
