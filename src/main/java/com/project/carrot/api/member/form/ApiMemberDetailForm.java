package com.project.carrot.api.member.form;

import com.project.carrot.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiMemberDetailForm {

    private Long memberId;
    private String loginId;
    private String nickname;
    private String email;
    private String contact;
    private List<String> address;
    private String profileImg;


    @Builder
    public ApiMemberDetailForm(Member member) {
        this.memberId = member.getMemberId();
        this.loginId = member.getLoginId();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.contact = member.getContact();
        this.address = List.of(member.getAddress().toString());
        this.profileImg = member.getStoredImageName();
    }
}
