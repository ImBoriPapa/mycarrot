package com.project.carrot.api.member;

import com.project.carrot.api.member.form.ApiMemberDetailForm;
import com.project.carrot.api.member.form.RequestMemberListForm;
import com.project.carrot.api.member.form.request.RequestRegisterForm;
import com.project.carrot.api.member.form.response.ResponseRegisterForm;
import com.project.carrot.domain.member.dto.RegisteMemberDto;
import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.service.MemberService;
import com.project.carrot.exception.customEx.RequestValidationException;
import com.project.carrot.utlis.header.ResponseHeader;
import com.project.carrot.utlis.response.CustomResponseStatus;
import com.project.carrot.utlis.response.ResponseForm;
import com.project.carrot.utlis.validator.RequestRegisterFormValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
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

import static com.project.carrot.exception.errorCode.ErrorCode.VALID_FAIL_ERROR;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberApiController {

    private final RequestRegisterFormValidator requestRegisterFormValidator;
    private final MemberService memberService;


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
    public ResponseEntity<Object> signUp(@RequestBody @Validated RequestRegisterForm form, BindingResult bindingResult) {
        log.info("POST : Member");

        if (bindingResult.hasErrors()) {
            throw new RequestValidationException(VALID_FAIL_ERROR, bindingResult);
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

        responseRegisterForm.add(linkTo.withRel("GET: Find All Member"));
        responseRegisterForm.add(linkTo.slash(member.getMemberId()).withRel("GET: Find One Member"));
        responseRegisterForm.add(linkTo.slash(member.getMemberId()).withRel("PUT: Update of Member"));
        responseRegisterForm.add(linkTo.slash(member.getMemberId()).withRel("DELETE: delete of Member"));

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
    public ResponseEntity findMembers(Pageable pageable) {
        log.info("GET : MEMBER");

        Page<Member> memberList = memberService.findMemberList(pageable);

        RequestMemberListForm requestMemberListForm = new RequestMemberListForm(memberList);

        List<Link> links = generateQueryStringLinks(requestMemberListForm);

        requestMemberListForm.getMemberList().stream().forEach(link -> link.add(links));

        ResponseForm<Object> responseForm = new ResponseForm<>(CustomResponseStatus.SUCCESS, requestMemberListForm);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, ResponseHeader.APPLICATION_JSON);
        headers.add(HttpHeaders.CONTENT_TYPE, ResponseHeader.APPLICATION_JSON_UTF8);


        return ResponseEntity.ok().headers(headers).body(responseForm);
    }

    private List<Link> generateQueryStringLinks(RequestMemberListForm form) {
        int currentPageNumber = form.getCurrentPageNumber();
        int previousPageNumber = form.getPreviousPageNumber();
        int nextPageNumber = form.getNextPageNumber();

        Link current = linkTo(MemberApiController.class).slash("?page=" + currentPageNumber).withRel("Now Page");
        Link previous = linkTo(MemberApiController.class).slash("?page=" + previousPageNumber).withRel("Previous Page");
        Link next = linkTo(MemberApiController.class).slash("?page=" + nextPageNumber).withRel("Next Page");
        List<Link> links = List.of();
        if (form.isHasPrevious() == false && form.isHasNext()) {
            links = List.of(current, next);
            return links;
        }
        if (form.isHasPrevious() && form.isHasNext() == false) {
            links = List.of(current, previous);
            return links;
        }
        links = List.of(current, previous, next);
        return links;
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



    @PostMapping("/test")
    public Map userResponseTest() {
        Map<String, String> result = new HashMap<>();
        result.put("result", "user ok");
        return result;
    }



}
