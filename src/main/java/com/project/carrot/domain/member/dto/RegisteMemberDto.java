package com.project.carrot.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisteMemberDto {
    private String loginId;
    private String password;
    private String nickname;
    private String email;
    private int addressCode;
    private String contact;
}
