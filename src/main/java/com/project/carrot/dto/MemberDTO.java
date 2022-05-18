package com.project.carrot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

    private Long id;
    private String member_id;
    private String member_password;
    private String member_nickname;

    public MemberDTO(){};

    public MemberDTO(Long id, String member_id, String member_password, String member_nickname) {
        this.id = id;
        this.member_id = member_id;
        this.member_password = member_password;
        this.member_nickname = member_nickname;
    }
}
