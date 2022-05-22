package com.project.carrot.controller;

import com.project.carrot.dto.MemberDTO;
import com.project.carrot.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;



@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;


    @PostMapping("/login")
    public String login(HttpServletRequest request, String id, String pw ) throws Exception {

        boolean checkExitsId = memberService.checkExitsId(id);     //1.로그인시 존재하는 회원인지 확인
        boolean checkIdAndPw = memberService.checkIdAndPw(id, pw); //2.로그인시 id와비밀번호 일치하는지 확인

        if(checkExitsId){
            //1-2 존재하는 회원명이 있을때 비밀번호를 체크한다.

            if(checkIdAndPw){
                return "/board/mainPage";
            }else{
                return "/member/loginPage";
            }
        }else {
            //1-2 존재하는 회원명이 없으면 회원가입페이지로 보낸다.

            return "/member/form";
        }
    }

    @PostMapping("/singUp")
    public String signUp(String memberId,String memberPw,String memberNickname){
        //1.저장기능
        MemberDTO signUpDto = new MemberDTO();
        signUpDto.setMember_id(memberId);
        signUpDto.setMember_password(memberPw);
        signUpDto.setMember_nickname(memberNickname);
        memberService.saveMember(signUpDto);
        //2.중복회원검증

        //3.null값 체크
        return "/member/loginPage";
    }






}
