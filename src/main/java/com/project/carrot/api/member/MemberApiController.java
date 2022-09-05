package com.project.carrot.api.member;

import com.project.carrot.api.member.form.ApiCreateMemberForm;
import com.project.carrot.api.response.BaseResponse;
import com.project.carrot.api.response.CustomResponseStatus;
import com.project.carrot.domain.member.dto.CreateMemberDto;
import com.project.carrot.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApiController {

    private final ApiCreateMemberFormValidator createMemberFormValidator;
    private final MemberService memberService;

    @InitBinder
    public void init(WebDataBinder dataBinder){
        dataBinder.addValidators(createMemberFormValidator);
    }

    @GetMapping("/login-page")
    public ModelAndView accessLoginPage() {
        log.info("access/success api/login-page");
        return new ModelAndView("api/member/api-loginPage");
    }

    @GetMapping("/signUp-page")
    public ModelAndView accessSignUpPage() {
        log.info("access/success api/signUp-page");
        return new ModelAndView("api/member/api-signUpPage");
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object>signUp(@RequestBody @Validated ApiCreateMemberForm form, BindingResult bindingResult) {
        log.info("call sign up");
        if (bindingResult.hasErrors()) {
            log.error("error={}",bindingResult);
            BaseResponse baseResponse = new BaseResponse<>().failValidationByBeanResponse(HttpStatus.BAD_REQUEST, CustomResponseStatus.REQUEST_ERROR, List.of(form), bindingResult);
            return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
        }

        CreateMemberDto memberDto = CreateMemberDto.builder()
                .loginId(form.getLoginId())
                .password(form.getPassword())
                .nickname(form.getNickname())
                .email(form.getEmail())
                .contact(form.getContact())
                .address(form.getAddress()).build();
        memberService.saveMember(memberDto);
        BaseResponse baseResponse = new BaseResponse<>().successResponse(CustomResponseStatus.SUCCESS,List.of(form));

        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }


}
