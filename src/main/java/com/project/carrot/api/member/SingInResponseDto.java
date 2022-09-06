package com.project.carrot.api.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingInResponseDto {
    private String accessToken;
    private String refreshToken;
}
