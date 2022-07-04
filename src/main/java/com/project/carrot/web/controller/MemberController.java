package com.project.carrot.web.controller;

import com.project.carrot.dto.MemberDTO;
import com.project.carrot.domain.service.MemberService;
import com.project.carrot.dto.MemberList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login") //로그인폼 접속
    public String login(){

        return "/member/loginForm";
    }

    @PostMapping("/login")
    public String login(String id, String pw ) throws Exception {


        MemberDTO checkIdAndPw = memberService.checkIdAndPw(id, pw); //2.로그인시 id와비밀번호 일치하는지 확인

        if(checkIdAndPw == null) {//checkIdAndPw이 null 이라면 로그인 실패
            return "/";
        }
        return "/";
    }


    @GetMapping("/signUp") //회원가입페이지 접속
    public String signUpForm(@ModelAttribute("memberDTO") MemberDTO memberDTO){

        return "/member/signUpForm";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid @ModelAttribute MemberDTO memberDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "member/signUpForm";
        }

        //1.중복아이디검증 : 결과가 false 이면 존재하는 회원 true 이면 존재하지 않는 회원
        boolean checkExitsId = memberService.checkUserId(memberDTO.getUserId());

        boolean checkExitsEmail = memberService.checkEmail(memberDTO.getEmail());
        log.info("checkExitId , checkExitsEmail = [{}][{}]",checkExitsId,checkExitsEmail);

        if(checkExitsId){
            return "member/signUpForm";
        }else if(checkExitsEmail){
            return "member/signUpForm";
        }
        //회원정보 저장
        memberService.saveMember(memberDTO);
        return "redirect:/member/login";
    }

    @GetMapping("/members")
    public String members(Model model){
        ArrayList<MemberList> memberList = memberService.members();

        model.addAttribute("memberList",memberList);

        return "/member/members";
    }










}
