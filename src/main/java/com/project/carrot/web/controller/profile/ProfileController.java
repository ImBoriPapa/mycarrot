package com.project.carrot.web.controller.profile;

import com.project.carrot.domain.member.CurrentMember;
import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.domain.member.service.MemberService;
import com.project.carrot.domain.profile.service.ProfileService;
import com.project.carrot.web.controller.profile.dto.ProfileForm;
import com.project.carrot.web.controller.profile.dto.UpdateProfileForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final MemberService memberService;
    private final ProfileService profileService;

    protected final String PATH_ID = "/{memberId}"; // /{memberId}
    protected final String TO_PROFILE_VIEW = "profile/profile";

    protected final String UPDATE_PATH = "/update/{memberId}";

    protected final String REDIRECT_HOME ="redirect:/";

    protected final String TO_UPDATE_PROFILE_FORM = "profile/updateProfileForm";

    protected final String REDIRECT_UPDATE_FORM = "redirect:/update/{memberId}";

    protected final String REDIRECT_PROFILE = "redirect:/profile/";

    protected final String DOWNLOAD_PROFILE_IMAGE_PATH = "/profileImages/{imageName}";

    @GetMapping(PATH_ID)
    public String profile(@PathVariable String memberId, Model model, @CurrentMember Member member) {

        if (member == null) {
            return "redirect:/";
        }

        Member findMember = memberService.findMember(Long.valueOf(memberId));

        ProfileForm profileForm = new ProfileForm();
        profileForm.setNickname(findMember.getNickname());
        profileForm.setEmail(findMember.getEmail());
        profileForm.setContact(findMember.getContact());
        profileForm.setImageName(findMember.getStoredImageName());
        profileForm.setFirstAddress(findMember.getAddress().get(0).getDong());
        profileForm.setSecondAddress(findMember.getAddress().get(1).getDong());

        log.info("imageName={}", findMember.getStoredImageName());

        model.addAttribute("profileForm", profileForm);
        model.addAttribute("isOwner", findMember.equals(member));
        return TO_PROFILE_VIEW;
    }

    @GetMapping(UPDATE_PATH)
    public String updateProfileForm(@PathVariable String memberId, Model model, @CurrentMember Member member) {

        if (member == null) {
            return REDIRECT_HOME;
        }
        Member findMember = memberService.findMember(Long.valueOf(memberId));

        ProfileForm profileForm = new ProfileForm();
        profileForm.setNickname(findMember.getNickname());
        profileForm.setImageName(findMember.getStoredImageName());


        model.addAttribute("profileForm", profileForm);


        return TO_UPDATE_PROFILE_FORM;
    }

    @PostMapping(UPDATE_PATH)
    public String updateProfile(@PathVariable String memberId, @Valid @ModelAttribute UpdateProfileForm updateProfileForm, BindingResult bindingResult
            , @CurrentMember Member member
            , RedirectAttributes redirectAttributes) throws IOException {
        if (bindingResult.hasErrors()) {
            log.info("has error");
            redirectAttributes.addAttribute("updateProfileForm", updateProfileForm);
            return REDIRECT_UPDATE_FORM;
        }

        if (updateProfileForm.getImageName().isEmpty()) {
            profileService.updateNickname(Long.valueOf(member.getMemberId()), updateProfileForm.getNickname());
            return REDIRECT_PROFILE + memberId;
        }

        profileService.updateProfile(Long.valueOf(member.getMemberId()), updateProfileForm.getImageName(), updateProfileForm.getNickname());


        return REDIRECT_PROFILE + memberId;
    }

    @ResponseBody
    @GetMapping(DOWNLOAD_PROFILE_IMAGE_PATH)
    public Resource downloadImage(@PathVariable String imageName) throws MalformedURLException {
        String fullPath = profileService.getFullPath(imageName);
        log.info("full path={}", fullPath);
        return new UrlResource("file:" + fullPath);

    }
}
