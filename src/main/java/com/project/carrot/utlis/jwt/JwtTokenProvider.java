package com.project.carrot.utlis.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenProvider {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("spring.jwt.secrete")
    private String SECRET_KEY;
    
    /**
     * Token 유효 시간
     */
    private long tokenValidMillisecond = 1000L * 60 * 30;

    private final UserDetailsService userDetailsService;

    /**
     * 객체 초기화 , SECRET_KEY 를 Base64 로 인코딩
     */
    @PostConstruct
    protected void init(){
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    /**
     * Token 생성
     */
    public String createToken(Authentication authentication){

        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidMillisecond);
        return Jwts.builder()
                .setSubject(authentication.getName())//정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(validity) //만료시간 설정
                /**
                 * 사용할 암호화 알고리즘  SignatureAlgorithm.HS256
                 * signature 에 들어갈 SECRET_KEY
                 */
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
    }
    private String createRefreshToken(Authentication authentication){
        Date now = new Date();
        Date validity = new Date(now.getTime());
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .setExpiration(validity)
                .compact();
    }

    @Transactional
    public String reissueRefreshToken(String refreshToken)throws RuntimeException{
        Authentication authentication = getAuthentication(refreshToken);
        RefreshToken findRefreshToken = refreshTokenRepository.findByMemberId(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("memberId : " + authentication.getName() + "was not found"));

        if(findRefreshToken.getToken().equals(refreshToken)){
            String newRefreshToken = createRefreshToken(authentication);
            findRefreshToken.changeToken(newRefreshToken);
            return newRefreshToken;
        }
        else {
            return null;
        }
    }
    @Transactional
    public String issuedRefreshToken(Authentication authentication){
        String newRefreshToken = createRefreshToken(authentication);

        refreshTokenRepository.findByMemberId(authentication.getName())
                .ifPresentOrElse(
                        r->{r.changeToken(newRefreshToken);},
                        ()->{
                            RefreshToken token = RefreshToken.createToken(authentication.getName(), newRefreshToken);
                            refreshTokenRepository.save(token);
                        }
                );
        return newRefreshToken;
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token){

        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    // Request 의 Header 에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN 값'
    public String resolveToken(HttpServletRequest request){
        return request.getHeader("X-AUTH-TOKEN");
    }

    // 토큰의 유효성 + 만료일자 확인
    public JwtCode validateToken(String jwtToken){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken);
            return JwtCode.ACCESS;
        }catch (ExpiredJwtException e){
            return JwtCode.EXPIRED;
        }catch (JwtException | IllegalArgumentException e){
            log.info("refresh 토큰이 일치하지 않습니다. ");
        }
        return JwtCode.DENIED;
    }
    public static enum JwtCode{
        DENIED,ACCESS,EXPIRED;
    }
}
