package com.project.carrot.web.controller.profile.dto;

import lombok.Data;

@Data
public class ProfileForm {

    private String nickname;
    private String email;
    private String image;
    private String contact;
    private String firstAddress;
    private String SecondAddress;

    public ProfileForm() {
    }


}
