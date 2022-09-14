package com.project.carrot.api.member.form.response;

import com.project.carrot.domain.member.entity.Member;
import com.project.carrot.utlis.form.ResultForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * extends ResultForm
 *
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRegisterForm extends ResultForm {
    private Long memberId;
    private LocalDateTime signUpDate;

    public ResponseRegisterForm(Member member) {
        this.memberId = member.getMemberId();
        this.signUpDate = member.getSignUpDate();
    }
}
