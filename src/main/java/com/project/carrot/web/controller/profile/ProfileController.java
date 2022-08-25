package com.project.carrot.web.controller.profile;

import com.project.carrot.domain.member.CurrentMember;
import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.service.MemberService;
import com.project.carrot.web.controller.profile.dto.ProfileForm;
import com.project.carrot.web.controller.profile.dto.UpdateProfileForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public String profile(@PathVariable String memberId, Model model, @CurrentMember Member member) {

        if(member == null){
            return "redirect:/";
        }

        Member findMember = memberService.findMember(Long.valueOf(memberId));

        ProfileForm profileForm = new ProfileForm();
        profileForm.setNickname(findMember.getNickname());
        profileForm.setEmail(findMember.getEmail());
        profileForm.setContact(findMember.getContact());
        profileForm.setFirstAddress(findMember.getAddress().get(0).getDong());
        profileForm.setSecondAddress(findMember.getAddress().get(1).getDong());

        model.addAttribute("profileForm", profileForm);
        model.addAttribute("isOwner", findMember.equals(member));
        return "profile/profile";
    }

    @GetMapping("/update/{memberId}")
    public String updateProfileForm(@PathVariable String memberId, Model model,@CurrentMember Member member) {

        if(member == null){
            return "redirect:/";
        }
        Member findMember = memberService.findMember(Long.valueOf(memberId));

        ProfileForm profileForm = new ProfileForm();
        profileForm.setNickname(findMember.getNickname());
        profileForm.setEmail(findMember.getEmail());
        profileForm.setContact(findMember.getContact());
        profileForm.setFirstAddress(findMember.getAddress().get(0).getDong());
        profileForm.setSecondAddress(findMember.getAddress().get(1).getDong());

        model.addAttribute("profileForm", profileForm);
        model.addAttribute("isOwner", findMember.equals(member));


        return "profile/updateProfileForm";
    }

    @PostMapping("/update/{memberId}")
    public String updateProfile(@PathVariable String memberId, @Valid @ModelAttribute UpdateProfileForm updateProfileForm, BindingResult bindingResult, @CurrentMember Member member){
        if (bindingResult.hasErrors()) {
            log.info("has error");
        }

        log.info("contact={}",updateProfileForm.getContact());
        log.info("email={}",updateProfileForm.getEmail());
        log.info("getFirstAddress={}",updateProfileForm.getFirstAddress());
        log.info("getSecondAddress={}",updateProfileForm.getSecondAddress());

        return "redirect:/profile/"+memberId;
    }

}
