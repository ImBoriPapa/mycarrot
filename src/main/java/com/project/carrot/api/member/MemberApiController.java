package com.project.carrot.api.member;

import com.project.carrot.api.member.form.ApiMemberDetailForm;
import com.project.carrot.api.member.form.ApiMemberListForm;
import com.project.carrot.api.member.form.request.RequestRegisterForm;
import com.project.carrot.api.member.form.response.ResponseRegisterForm;
import com.project.carrot.domain.member.dto.RegisteMemberDto;
import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.service.LoginService;
import com.project.carrot.domain.member.service.MemberService;
import com.project.carrot.exception.customEx.RequestValidationException;
import com.project.carrot.exception.errorCode.ErrorCode;
import com.project.carrot.utlis.header.ResponseHeader;
import com.project.carrot.utlis.response.CustomResponseStatus;
import com.project.carrot.utlis.response.ResponseForm;
import com.project.carrot.utlis.validator.RequestRegisterFormValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApiController {

    private final RequestRegisterFormValidator requestRegisterFormValidator;
    private final MemberService memberService;

    private final LoginService loginService;

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(requestRegisterFormValidator);
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

    /**
     * POST:/MEMBER - 회원 가입
     * Request - RequestRegisterForm
     * Response- ResponseEntity(ResponseForm)
     */
    @PostMapping
    public ResponseEntity<Object> signUp(@RequestBody @Validated RequestRegisterForm form, BindingResult bindingResult){
        log.info("POST : Member");

        if (bindingResult.hasErrors()) {
            throw new RequestValidationException(ErrorCode.VALID_FAIL_ERROR, bindingResult);
        }

        RegisteMemberDto registeMemberDto = RegisteMemberDto.builder()
                .loginId(form.getLoginId())
                .password(form.getPassword())
                .nickname(form.getNickname())
                .email(form.getEmail())
                .contact(form.getContact())
                .addressCode(form.getAddressCode()).build();

        Member member = memberService.createMember(registeMemberDto);
        ResponseRegisterForm responseRegisterForm = new ResponseRegisterForm(member);

        WebMvcLinkBuilder linkTo = linkTo(MemberApiController.class);

        responseRegisterForm.add(linkTo.withRel("Find All Member"));
        responseRegisterForm.add(linkTo.slash(member.getMemberId()).withRel("To Detail of Member"));
        responseRegisterForm.add(linkTo.slash(member.getMemberId()).slash("update").withRel("To Update of Member"));
        responseRegisterForm.add(linkTo.slash(member.getMemberId()).slash("delete").withRel("To delete of Member"));

        ResponseForm<Object> responseForm = new ResponseForm<>(CustomResponseStatus.SUCCESS, responseRegisterForm);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, ResponseHeader.APPLICATION_JSON);
        headers.add(HttpHeaders.CONTENT_TYPE, ResponseHeader.APPLICATION_JSON_UTF8);
        headers.add(HttpHeaders.LOCATION, linkTo.withSelfRel().toString());

        return ResponseEntity.created(linkTo.withSelfRel().toUri())
                .headers(headers)
                .body(responseForm);
    }

    /**
     * GET:/MEMBER 회원 전체 조회
     */
    @GetMapping
    public ResponseEntity findMembers() {
        log.info("GET : MEMBER");
        List<Member> memberList = memberService.findMemberList();
        List<ApiMemberListForm> collect = memberList.stream().map(ApiMemberListForm::new).collect(Collectors.toList());
        ResponseForm<Object> responseForm = new ResponseForm<>(CustomResponseStatus.SUCCESS, collect);
        return ResponseEntity.ok().body(responseForm);
    }

    /**
     * GET:/MEMBER/(LONG){memberId}
     * 회원 상세 조회
     */
    @GetMapping("/{memberId}")
    public ResponseEntity findMember(@PathVariable Long memberId) {
        Member member = memberService.findMember(memberId);

        ApiMemberDetailForm apiMemberDetailForm = ApiMemberDetailForm.builder()
                .member(member)
                .build();
        ResponseForm memberResponseForm = new ResponseForm<>(CustomResponseStatus.SUCCESS, apiMemberDetailForm);
        return ResponseEntity.ok().body(memberResponseForm);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) {
        log.info("loginId={}", loginDto.getLoginId());
        log.info("password={}", loginDto.getPassword());
        HttpHeaders headers = new HttpHeaders();

        return ResponseEntity.ok().headers(headers).body("ㅠㅠ");
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

        public LoginDto() {
        }

        public LoginDto(String loginId, String password) {
            this.loginId = loginId;
            this.password = password;
        }
    }

}
