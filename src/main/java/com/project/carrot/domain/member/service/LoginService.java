package com.project.carrot.domain.member.service;

import com.project.carrot.api.member.LoginResponseDto;
import com.project.carrot.exception.member_exception.WrongPasswordException;
import com.project.carrot.utlis.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.project.carrot.exception.errorCode.ErrorCode.WRONG_PASSWORD;
import static com.project.carrot.utlis.jwt.JwtHeader.JWT_HEADER_PREFIX;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * login() : 로그인 처리
     * @param loginId
     * @param password
     * password 일치하지 않을경우 WrongPasswordException
     * @return LoginResponseDto(createToken(),issuedRefreshToken())
     */
    @Transactional
    public LoginResponseDto login(String loginId, String password){
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new WrongPasswordException(WRONG_PASSWORD.getMessage());
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()
        );
        return new LoginResponseDto(
                JWT_HEADER_PREFIX+jwtTokenProvider.createToken(authentication),
                JWT_HEADER_PREFIX+jwtTokenProvider.issuedRefreshToken(authentication));
    }
}
