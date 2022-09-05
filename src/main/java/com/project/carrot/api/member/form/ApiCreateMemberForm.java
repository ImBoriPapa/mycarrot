package com.project.carrot.api.member.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiCreateMemberForm {
    @NotBlank(message = "아이디는 공백을 허용하지 않습니다.")
    @Size(min=5,max = 10,message = "5~10사이")
    private String loginId;
    @NotBlank
    @Size(min=5,max = 10,message = "5~10사이")
    private String password;
    @NotBlank(message = "반드시 입력해야 합니다.")
    private String nickname;
    @NotBlank(message = "반드시 입력해야 합니다.")
    @Email(message = "형식에 맞지 않는 이메일입니다.")
    private String email;
    private String contact;
    private String address;
}