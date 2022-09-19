package com.project.carrot.api.login.form;

import com.project.carrot.utlis.form.ResultForm;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseLoginForm extends ResultForm {

    private Long memberId;
    private LocalDateTime loginDate;

    public ResponseLoginForm(Long memberId) {
        this.memberId = memberId;
        this.loginDate = LocalDateTime.now();
    }
}
