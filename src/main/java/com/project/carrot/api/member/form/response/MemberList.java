package com.project.carrot.api.member.form.response;

import com.project.carrot.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberList extends EntityModel {

    private Long memberId;
    private String loginId;
    private String nickname;
    private String email;
    private String contact;
    private String address;

    @Builder
    public MemberList(Member member) {
        this.memberId = member.getMemberId();
        this.loginId = member.getLoginId();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.contact = member.getContact();
        this.address = member.getAddress().toString();
    }

}
