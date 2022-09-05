package com.project.carrot.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateMemberDto {
    private String loginId;
    private String password;
    private String nickname;
    private String email;
    private String address;
    private String contact;
}
