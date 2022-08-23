package com.project.carrot.web.controller.member.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginMemberForm {
    @NotBlank(message = "공백을 허용하지 않습니다.")
    private String loginId;
    @NotBlank(message = "공백을 허용하지 않습니다.")
    private String password;
}
