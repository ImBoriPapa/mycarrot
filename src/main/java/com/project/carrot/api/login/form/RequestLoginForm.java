package com.project.carrot.api.login.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLoginForm {

    @NotBlank(message = "아이디는 공백을 허용하지 않습니다.")
    private String loginId;
    @NotBlank(message = "비밀번호는 공백을 허용하지 않습니다.")
    private String password;

}
