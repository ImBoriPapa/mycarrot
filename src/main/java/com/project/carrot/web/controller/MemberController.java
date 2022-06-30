package com.project.carrot.web.controller;

import com.project.carrot.dto.MemberDTO;
import com.project.carrot.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/loginForm") //로그인폼 접속
    public String login(){

        return "member/loginForm";
    }


    @GetMapping("/signUpForm") //회원가입페이지 접속
    public String signUpForm(){

        return "member/signUpForm";
    }

    @PostMapping("/signUp")
    public String signUp(MemberDTO memberDTO, BindingResult bindingResult, Model model){

        //1.중복아이디검증 : 결과가 false 이면 존재하는 회원 true 이면 존재하지 않는 회원
        boolean checkExitsId = memberService.checkUserId(memberDTO.getUserId());
        log.info("checkExitId is = {}",checkExitsId);

        if(checkExitsId){ //
            return "redirect:/signUpForm";
        }else{

            //회원정보 저장
            memberService.saveMember(memberDTO);
            return "redirect:/boardList";
        }
    }


    @PostMapping("/login")
    public String login(HttpServletRequest request, String id, String pw ) throws Exception {

        boolean checkExitsId = memberService.checkUserId(id);     //1.로그인시 존재하는 회원인지 확인
        boolean checkIdAndPw = memberService.checkIdAndPw(id, pw); //2.로그인시 id와비밀번호 일치하는지 확인

        if(checkExitsId){
            //1-2 존재하는 회원명이 있을때 비밀번호를 체크한다.

            if(checkIdAndPw){
                return "board";
            }else{
                return "member/loginForm";
            }
        }else {
            //1-2 존재하는 회원명이 없으면 회원가입페이지로 보낸다.

            return "/member/form";
        }
    }








}
