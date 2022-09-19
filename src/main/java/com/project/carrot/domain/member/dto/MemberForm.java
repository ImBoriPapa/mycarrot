package com.project.carrot.domain.member.dto;

import com.project.carrot.domain.member.entity.MemberRoll;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberForm {

    private Long memberId;
    private String loginId;
    private String password;
    private MemberRoll memberRoll;

    public List<String> getRoll() {
        return List.of(memberRoll.getRollValue());
    }
}
