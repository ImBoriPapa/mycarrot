package com.project.carrot.web.controller.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

/**
 * 회원 가입 폼
 *BeanValidation
 */
@Getter
@Setter
public class CreateMemberForm {
    @NotBlank(message = "반드시 입력해야 합니다.")
    @Size(min=5,max = 10,message = "5~10사이")
//    @Pattern(regexp = "^[a-z]+[a-z0-9]{5,19}$", message = "영문자 + 숫자만 가능합니다.")
    private String loginId;

    @NotBlank
    @Size(min=5,max = 10,message = "5~10사이")
    private String password;

    @NotBlank(message = "반드시 입력해야 합니다.")
    private String nickname;

    @NotBlank(message = "반드시 입력해야 합니다.")
    @Email(message = "형식에 맞지 않는 이메일입니다.")
    private String email;


    public CreateMemberForm(String loginId, String password, String nickname, String email) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;

    }

}
