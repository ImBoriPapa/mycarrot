package com.project.carrot.domain.Login.service.dto;

import lombok.Data;

@Data
public class LoginDto {

    private Long memberId;
    private String token;
    private String refreshToken;

    public LoginDto(Long memberId,String token, String refreshToken) {
        this.memberId = memberId;
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
