package com.project.carrot.web.controller;

import com.project.carrot.dto.MemberDto;
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

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

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
    public String login(@Valid @ModelAttribute LoginDto loginDto,BindingResult bindingResult) throws Exception {

        if(bindingResult.hasErrors()){ // 에러 발생시 로그인 폼으로 이동
           log.info("hasErrors() {}",bindingResult);
            return "/member/loginForm";
        }

        MemberDto checkIdAndPw = memberService.checkIdAndPw(loginDto.userId, loginDto.password); //아이디와 비밀번호를 검사해서 존재하면 dto반환 없으면 null

        if(checkIdAndPw == null){//checkIdAndPw이 null 이라면 로그인 실패 로그인페이지로 이동
        log.info("checkIdAndPw = {}",checkIdAndPw);
            return "/member/loginForm";
        }
        log.info("checkIdAndPw = {}",checkIdAndPw.getUserId());
        return  "redirect:/board"; //성공시 보드 페이지로 이동
    }

    private static class LoginDto{ // 로그인시 필요한 정보만을 가지는 dto
        @NotBlank(message = "아이디를 확인해주세요")
        private String userId;
        @NotBlank(message = "비밀번호를 확인해주세요")
        private String password;

        public LoginDto(String userId, String password) {
            this.userId = userId;
            this.password = password;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


    @GetMapping("/signUp") //회원가입페이지 접속
    public String signUpForm(@ModelAttribute("memberDTO") MemberDto memberDTO){

        return "/member/signUpForm";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid @ModelAttribute MemberDto memberDTO, BindingResult bindingResult){

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
