package com.project.carrot.api.member;

import com.project.carrot.api.member.form.ApiCreateMemberForm;
import com.project.carrot.api.member.form.ApiMemberDetailForm;
import com.project.carrot.api.member.form.ApiMemberListForm;
import com.project.carrot.utlis.response.CustomResponseStatus;
import com.project.carrot.utlis.response.ResponseForm;
import com.project.carrot.domain.member.dto.CreateMemberDto;
import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.service.LoginService;
import com.project.carrot.domain.member.service.MemberService;
import com.project.carrot.exception.BasicException;
import com.project.carrot.exception.errorCode.ErrorCode;
import com.project.carrot.utlis.header.ResponseHeader;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberApiController {

    private final ApiCreateMemberFormValidator createMemberFormValidator;
    private final MemberService memberService;

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

    /**
     * POST:/MEMBER - 회원 가입
     */
    @PostMapping
    public ResponseEntity<Object> signUp(@RequestBody @Validated ApiCreateMemberForm form, BindingResult bindingResult) throws URISyntaxException {
        log.info("POST : Member");
        if (bindingResult.hasErrors()) {
            throw new BasicException(ErrorCode.VALID_FAIL_ERROR, bindingResult);
        }

        CreateMemberDto memberDto = CreateMemberDto.builder()
                .loginId(form.getLoginId())
                .password(form.getPassword())
                .nickname(form.getNickname())
                .email(form.getEmail())
                .contact(form.getContact())
                .address(form.getAddress()).build();


        Long getId = memberService.saveMember(memberDto);
        Long memberId = getId;

        WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(MemberApiController.class);
        Link withSelfRel = webMvcLinkBuilder.withSelfRel();

        ResponseForm<Object> responseForm = new ResponseForm<>(CustomResponseStatus.SUCCESS, memberId);
        responseForm.add(withSelfRel);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT,ResponseHeader.APPLICATION_JSON);
        headers.add(HttpHeaders.CONTENT_TYPE,ResponseHeader.APPLICATION_JSON_UTF8);
        headers.add(HttpHeaders.LOCATION, withSelfRel.getHref());

        return ResponseEntity.created(withSelfRel.toUri())
                .headers(headers)
                .body(responseForm);

    }
    /**
     *GET:/MEMBER 회원 전체 조회
     */
    @GetMapping
    public ResponseEntity findMembers() {
        log.info("GET : MEMBER");
        List<Member> memberList = memberService.findMemberList();
        List<ApiMemberListForm> collect = memberList.stream().map(ApiMemberListForm::new).collect(Collectors.toList());
        ResponseForm<Object> responseForm = new ResponseForm<>(CustomResponseStatus.SUCCESS,collect);
        return ResponseEntity.ok().body(responseForm);
    }

    /**
     * GET:/MEMBER/(LONG){memberId}
     * 회원 상세 조회
     */
    @GetMapping("/{memberId}")
    public ResponseEntity findMember(@PathVariable Long memberId){
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
