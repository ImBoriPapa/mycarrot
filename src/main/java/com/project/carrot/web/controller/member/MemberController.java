package com.project.carrot.web.controller.member;

import com.project.carrot.domain.address.entity.Address;
import com.project.carrot.domain.member.CurrentMember;
import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.service.MemberService;
import com.project.carrot.web.controller.member.dto.CreateMemberForm;
import com.project.carrot.web.controller.member.dto.LoginMemberForm;
import com.project.carrot.web.controller.member.dto.MemberList;
import com.project.carrot.web.controller.member.dto.MemberProfileDto;
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

    @GetMapping("/login") //로그인폼 접속
    public String login(@ModelAttribute("loginMemberForm") LoginMemberForm loginMemberForm,
                        @RequestParam(value = "error",required = false)String error,
                        @RequestParam(value = "message",required = false)String message,
                        Model model) {
        log.info("error={}",error);
        log.info("message={}",message);
        model.addAttribute("error", error);
        model.addAttribute("message", message);

        return "member/loginForm";
    }


    //회원가입페이지 이동
    @GetMapping("/sign-up")
    public String signUpForm(@ModelAttribute("createMemberForm") CreateMemberForm createMemberForm) {
        return "member/signUpForm";
    }

    //회원가입 정보 검증 및 저장
    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute CreateMemberForm createMemberForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("error ={}", bindingResult);
            return "member/signUpForm";
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

    @GetMapping("/profile/{nickname}")
    public String viewProfile(@PathVariable String nickname, Model model, @CurrentMember Member member) {
        log.info("nickname ={}",nickname);
        log.info("member ={}",member);
        Member findMember = memberService.findMember(member.getMemberId());

        MemberProfileDto memberProfileDto = new MemberProfileDto();
        memberProfileDto.setNickname(findMember.getNickname());
        memberProfileDto.setEmail(findMember.getEmail());
        memberProfileDto.setContact(findMember.getContact());
        memberProfileDto.setFirstAddress(findMember.getAddress().get(0).getDong());
        memberProfileDto.setSecondAddress(findMember.getAddress().get(1).getDong());




        if (member == null) {
            throw new IllegalArgumentException(nickname + "에 해당하는 사용자가 없습니다.");
        }


        model.addAttribute("member", memberProfileDto);
        model.addAttribute("isOwner", findMember.equals(member));
        return "member/profile";
    }

}
