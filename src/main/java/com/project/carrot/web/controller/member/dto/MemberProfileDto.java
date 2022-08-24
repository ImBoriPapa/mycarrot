package com.project.carrot.web.controller.member.dto;

import lombok.Data;

@Data
public class MemberProfileDto {

    private String nickname;
    private String email;
    private String image;
    private String contact;
    private String firstAddress;
    private String SecondAddress;

    public MemberProfileDto() {
    }


}
