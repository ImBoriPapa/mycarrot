package com.project.carrot.web.controller.profile.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateProfileForm {

    private String nickname;
    private MultipartFile imageName;

    public UpdateProfileForm() {
    }

    public UpdateProfileForm(String nickname, MultipartFile imageName) {
        this.nickname = nickname;
        this.imageName = imageName;
    }

}
