package com.project.carrot.api.member.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiCreateSignUpForm {
    @NotBlank(message = "아이디는 공백을 허용하지 않습니다.")
    private String loginId;
    @NotBlank(message = "비밀번호는 공백을 허용하지 않습니다.")
    private String password;
    private String nickname;
    private String email;
    private String address;
}
