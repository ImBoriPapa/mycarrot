package com.project.carrot.api.member;

import com.project.carrot.api.header.ResponseHeader;
import com.project.carrot.api.member.form.ApiCreateMemberForm;
import com.project.carrot.api.response.BaseResponse;
import com.project.carrot.api.response.CustomResponseStatus;
import com.project.carrot.domain.member.dto.CreateMemberDto;
import com.project.carrot.domain.member.reposiotory.MemberRepository;
import com.project.carrot.domain.member.service.LoginService;
import com.project.carrot.domain.member.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.project.carrot.utlis.jwt.JwtHeader.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApiController {

    private final ApiCreateMemberFormValidator createMemberFormValidator;
    private final MemberService memberService;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final LoginService loginService;

    //    @InitBinder
    public void init(WebDataBinder dataBinder) {
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
    public ResponseEntity<Object> signUp(@RequestBody @Validated ApiCreateMemberForm form, BindingResult bindingResult) {
        log.info("call sign up");
        if (bindingResult.hasErrors()) {
            log.error("error={}", bindingResult);
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
        BaseResponse baseResponse = new BaseResponse<>().successResponse(CustomResponseStatus.SUCCESS, List.of(form));

        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) {
        log.info("loginId={}", loginDto.getLoginId());
        log.info("password={}", loginDto.getPassword());
        LoginResponseDto login = loginService.login(loginDto.loginId, loginDto.password);
        HttpHeaders headers = new HttpHeaders();
        headers.add(ResponseHeader.ACCEPT.getKEY(), ResponseHeader.ACCEPT.getVALUE());
        headers.add(ResponseHeader.CONTENT_TYPE.getKEY(), ResponseHeader.CONTENT_TYPE.getVALUE());
        headers.add(AUTHORIZATION_HEADER, JWT_HEADER_PREFIX + login.getAccessToken());
        headers.add(REFRESH_HEADER, JWT_HEADER_PREFIX + login.getRefreshToken());


        return ResponseEntity.ok().headers(headers).body("AUTHORIZATION_HEADER = "+login.getAccessToken()+System.lineSeparator()+"REFRESH_HEADER= "+login.getRefreshToken());
    }

    @PostMapping("/test")
    public Map userResponseTest() {
        Map<String, String> result = new HashMap<>();
        result.put("result", "user ok");
        return result;
    }

    @Data
    static class LoginDto {
        private String loginId;
        private String password;
    }

}
