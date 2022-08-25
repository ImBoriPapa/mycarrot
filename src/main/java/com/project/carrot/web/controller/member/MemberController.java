package com.project.carrot.web.controller.member;

import com.project.carrot.domain.address.entity.Address;
import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.service.MemberService;
import com.project.carrot.web.controller.member.dto.CreateMemberForm;
import com.project.carrot.web.controller.member.dto.LoginMemberForm;
import com.project.carrot.web.controller.member.dto.MemberList;
import com.project.carrot.web.controller.member.validation.CreateMemberFormValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final CreateMemberFormValidator createMemberFormValidator;


    @InitBinder("createMemberForm")
    @PostConstruct
    public void initData() {
        CreateMemberForm createMemberForm = new CreateMemberForm();
        createMemberForm.setLoginId("test");
        createMemberForm.setPassword("cszc7348!@");
        createMemberForm.setNickname("tester");
        createMemberForm.setEmail("test@test.com");
        createMemberForm.setContact("010-1111-1111");
        createMemberForm.setDistrict("서울시");
        createMemberForm.setTown("강서구");
        createMemberForm.setDong("우장산동");

        ArrayList<Address> address = new ArrayList<>();

        address.add(new Address(createMemberForm.getDistrict(), createMemberForm.getTown(), createMemberForm.getDong()));

        Member newMember = Member.builder()
                .loginId(createMemberForm.getLoginId())
                .password(createMemberForm.getPassword())
                .nickname(createMemberForm.getNickname())
                .contact(createMemberForm.getContact())
                .email(createMemberForm.getEmail())
                .address(address).build();

        memberService.saveMember(newMember);
    }


    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(createMemberFormValidator);
    }

    static final String LOGIN_URL = "/login";
    static final String LOGIN_FORM = "member/loginForm";
    static final String SIGNUP_URL = "/sign-up";
    static final String SIGNUP_FORM = "member/signUpForm";


    @GetMapping(LOGIN_URL) //로그인폼 접속
    public String login(@ModelAttribute("loginMemberForm") LoginMemberForm loginMemberForm,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "message", required = false) String message,
                        Model model) {
        log.info("error={}", error);
        log.info("message={}", message);
        model.addAttribute("error", error);
        model.addAttribute("message", message);

        return LOGIN_FORM;
    }

    //회원가입페이지 이동
    @GetMapping(SIGNUP_URL)
    public String signUpForm(@ModelAttribute("createMemberForm") CreateMemberForm createMemberForm) {
        return SIGNUP_FORM;
    }

    //회원가입 정보 검증 및 저장
    @PostMapping(SIGNUP_URL)
    public String signUp(@Valid @ModelAttribute CreateMemberForm createMemberForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("error ={}", bindingResult);
            return SIGNUP_FORM;
        }

        ArrayList<Address> address = new ArrayList<>();

        address.add(new Address(createMemberForm.getDistrict(), createMemberForm.getTown(), createMemberForm.getDong()));

        Member newMember = Member.builder()
                .loginId(createMemberForm.getLoginId())
                .password(createMemberForm.getPassword())
                .nickname(createMemberForm.getNickname())
                .contact(createMemberForm.getContact())
                .email(createMemberForm.getEmail())
                .address(address).build();

        memberService.saveMember(newMember);
        return "redirect:" + SIGNUP_URL;
    }

    @GetMapping("/members")
    public String members(Model model) {
        List<Member> members = memberService.findMemberList();
        ArrayList<MemberList> memberList = new ArrayList<>();

        for (Member member : members) {
            memberList.add(new MemberList.MemberListBuilder(member).builder());
        }

        model.addAttribute("memberList", memberList);

        return "/member/members";
    }


    @GetMapping("/success")
    private String success() {
        return "home/success";
    }


}
