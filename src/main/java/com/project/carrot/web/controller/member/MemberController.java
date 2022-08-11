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
    public String login(){

        return "/member/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginDto loginDto,BindingResult bindingResult) throws Exception {

        if(bindingResult.hasErrors()){ // 에러 발생시 로그인 폼으로 이동
           log.info("hasErrors() {}",bindingResult);
            return "/member/loginForm";
        }

        Member checkIdAndPw = memberService.checkIdAndPw(loginDto.userId, loginDto.password); //아이디와 비밀번호를 검사해서 존재하면 dto반환 없으면 null

        if(checkIdAndPw == null){//checkIdAndPw이 null 이라면 로그인 실패 로그인페이지로 이동
        log.info("checkIdAndPw = {}",checkIdAndPw);
            return "/member/loginForm";
        }
        log.info("checkIdAndPw = {}",checkIdAndPw.getLoginId());
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

    //회원가입페이지 이동
    @GetMapping("/sign-up")
    public String signUpForm(@ModelAttribute("createMemberForm") CreateMemberForm createMemberForm){
        return "/member/signUpForm";
    }

    //회원가입 정보 검증 및 저장
    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute CreateMemberForm createMemberForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.error("error ={}",bindingResult);
            return "member/signUpForm";
        }

        //회원정보 저장
        Member newMember =  Member.builder()
                .loginId(createMemberForm.getLoginId())
                .password(createMemberForm.getPassword())
                .nickname(createMemberForm.getNickname())
                .email(createMemberForm.getEmail())
                .build();
        memberService.saveMember(newMember);
        return "redirect:/member/login";
    }

    @GetMapping("/members")
    public String members(Model model){
        List<Member> members = memberService.findMemberList();
        ArrayList<MemberList> memberList = new ArrayList<>();

        for (Member member : members) {
           memberList.add(new MemberList.MemberListBuilder(member).builder());
        }

        model.addAttribute("memberList",memberList);

        return "/member/members";
    }










}
