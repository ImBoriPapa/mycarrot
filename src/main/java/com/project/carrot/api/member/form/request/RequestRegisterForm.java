package com.project.carrot.api.member.form.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter  @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestRegisterForm {
    @NotBlank(message = "로그인 아이디는 공백을 허용하지 않습니다.")
    @Size(min=5,max = 10,message = "아이디의 길이는 최소5자 최대 10자 사이 입니다.")
    private String loginId;
    @NotBlank(message = "패스워드는 공백을 허용하지 않습니다.")
    @Size(min=5,max = 10,message = "패스워드의 길이는 최소5자 ~최대 10자 사이입니다.")
    private String password;
    @NotBlank(message = "닉네임은 공백을 허용하지 않습니다.")
    @NotNull(message = "닉네임은 반드시 입력해야 합니다.")
    @Size(min=2,max = 10,message = "닉네임의 길이는 최소 2자 최대 10자 사이입니다.")
    private String nickname;
    @NotBlank(message = "이메일은 반드시 입력해야 합니다.")
    @Email(message = "형식에 맞지 않는 이메일입니다.")
    private String email;
    @NotBlank(message = "연락처를 반드시 입력해야 합니다.")
    private String contact;
    @NotNull(message = "회원가입시 하나의 주소는 반드시 입력해야 합니다.")
    @Min(value = 1000,message = "주소코드는 최소 1000이상 2000사이의 정수입니다.")
    @Max(value = 2000,message = "주소코드는 최소 1000이상 2000사이의 정수입니다.")
    private int addressCode;

}
