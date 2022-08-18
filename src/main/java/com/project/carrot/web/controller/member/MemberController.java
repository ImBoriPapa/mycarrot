package com.project.carrot.web.controller.member;

import com.project.carrot.domain.member.entity.Member;

import com.project.carrot.domain.member.service.MemberService;
import com.project.carrot.web.controller.member.validation.CreateMemberFormValidator;
import com.project.carrot.web.controller.member.dto.CreateMemberForm;
import com.project.carrot.web.controller.member.dto.MemberList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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

    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(createMemberFormValidator);
    }

    @GetMapping("/login") //로그인폼 접속
    public String login() {

        return "/member/loginForm";
    }

    //회원가입페이지 이동
    @GetMapping("/sign-up")
    public String signUpForm(@ModelAttribute("createMemberForm") CreateMemberForm createMemberForm) {
        return "/member/signUpForm";
    }

    //회원가입 정보 검증 및 저장
    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute CreateMemberForm createMemberForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("error ={}", bindingResult);
            return "member/signUpForm";
        }

        memberService.saveMember(createMemberForm);
        return "redirect:/member/login";
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
