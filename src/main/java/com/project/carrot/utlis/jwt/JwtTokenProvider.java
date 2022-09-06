package com.project.carrot.utlis.jwt;

import com.project.carrot.domain.member.entity.RefreshToken;
import com.project.carrot.domain.member.reposiotory.RefreshTokenRepository;
import com.project.carrot.exception.member_exception.NotMatchRefreshTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import static com.project.carrot.exception.errorCode.ErrorCode.NOT_MATCH_REFRESH_TOKEN;
import static com.project.carrot.exception.errorCode.ErrorCode.NO_RESULT_FIND_MEMBER;


@Component
@Slf4j
public class JwtTokenProvider implements InitializingBean {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserDetailsService userDetailsService;
    private final String SECRET_KEY;
    private final long tokenValidationMs;
    private final long refreshTokenValidationMs;
    private Key key;

    public JwtTokenProvider(RefreshTokenRepository refreshTokenRepository, UserDetailsService userDetailsService,
                            @Value("${jwt.secrete-key}") String secretKey,
                            @Value("${jwt.token-validity-in-sec}") long tokenValidationMs,
                            @Value("${jwt.refresh-token-validity-in-sec}") long refreshTokenValidationMinutes) {

        this.refreshTokenRepository = refreshTokenRepository;
        this.userDetailsService = userDetailsService;
        this.SECRET_KEY = secretKey;
        this.tokenValidationMs = tokenValidationMs * 1000;
        this.refreshTokenValidationMs = refreshTokenValidationMinutes * 1000;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String encodedKey = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
        key = Keys.hmacShaKeyFor(encodedKey.getBytes());
    }
    /**
     * Token 생성
     */
    public String createToken(Authentication authentication) {

        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidationMs);
        return Jwts.builder()
                .setSubject(authentication.getName())//정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(validity) //만료시간 설정
                .signWith(key, SignatureAlgorithm.HS512)//암호화
                .compact();
    }

    private String createRefreshToken(Authentication authentication) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenValidationMs);
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(now)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    @Transactional
    public String reissueRefreshToken(String refreshToken) throws RuntimeException {
        Authentication authentication = getAuthentication(refreshToken);
        RefreshToken findRefreshToken = refreshTokenRepository.findByMemberId(Long.valueOf(authentication.getName()))
                .orElseThrow(() -> new UsernameNotFoundException(authentication.getName() + NO_RESULT_FIND_MEMBER.getMessage()));

        if (findRefreshToken.getToken().equals(refreshToken)) {
            String newRefreshToken = createRefreshToken(authentication);
            findRefreshToken.changeToken(newRefreshToken);
            return newRefreshToken;
        } else {
            throw new NotMatchRefreshTokenException(NOT_MATCH_REFRESH_TOKEN.getMessage());
        }
    }

    @Transactional
    public String issuedRefreshToken(Authentication authentication) {
        String newRefreshToken = createRefreshToken(authentication);

        refreshTokenRepository.findByMemberId(Long.valueOf(authentication.getName()))
                .ifPresentOrElse(
                        r -> {
                            r.changeToken(newRefreshToken);
                        },
                        () -> {
                            RefreshToken token = RefreshToken.createToken(Long.valueOf(authentication.getName()), newRefreshToken);
                            refreshTokenRepository.save(token);
                        });
        return newRefreshToken;
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserPk(token).getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public Claims getUserPk(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    // 토큰의 유효성 + 만료일자 확인
    public JwtCode validateToken(String jwtToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);
            return JwtCode.ACCESS;
        } catch (ExpiredJwtException e) {
            return JwtCode.EXPIRED;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("refresh 토큰이 일치하지 않습니다. ");
        }
        return JwtCode.DENIED;
    }



    public static enum JwtCode {
        DENIED, ACCESS, EXPIRED;
    }


}
