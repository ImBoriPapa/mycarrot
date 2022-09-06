package com.project.carrot.utlis.jwt;

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

    private String memberId;
    private String token;

    private RefreshToken(String memberId, String token) {
        this.memberId = memberId;
        this.token = token;
    }

    public static RefreshToken createToken(String memberId, String token) {
        return new RefreshToken(memberId, token);
    }

    public void changeToken(String token) {
        this.token = token;
    }
}
