package com.project.carrot.domain.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class RefreshToken {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REFRESH_TOKEN_ID")
    private Long id;
    private Long memberId;
    private String token;

    private RefreshToken(Long memberId, String token) {
        this.memberId = memberId;
        this.token = token;
    }
    public static RefreshToken createToken(Long memberId, String token) {
        return new RefreshToken(memberId, token);
    }

    public void changeToken(String token) {
        this.token = token;
    }
}
