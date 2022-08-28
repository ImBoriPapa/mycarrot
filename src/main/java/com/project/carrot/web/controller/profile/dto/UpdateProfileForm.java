package com.project.carrot.web.controller.profile.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateProfileForm {
    @Length(min = 3,max = 10)
    @NotBlank(message = "공백을 허용하지 않습니다.")
    private String nickname;
    private MultipartFile imageName;

    public UpdateProfileForm() {
    }

    public UpdateProfileForm(String nickname, MultipartFile imageName) {
        this.nickname = nickname;
        this.imageName = imageName;
    }

}
