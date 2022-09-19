package com.project.carrot.domain.Login.service;

import com.project.carrot.domain.Login.service.dto.LoginDto;
import com.project.carrot.exception.customEx.FailToLoginException;
import com.project.carrot.utlis.jwt.JwtHeader;
import com.project.carrot.utlis.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.project.carrot.exception.errorCode.ErrorCode.FAIL_TO_LOGIN;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LoginService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailServiceImpl userDetailsService;

    /**
     * login() : 로그인 처리
     *
     * @param loginId
     * @param password password 일치하지 않을경우 WrongPasswordException
     * @return LoginResponseDto(createToken (), issuedRefreshToken())
     */
    @Transactional
    public LoginDto loginProcess(String loginId, String password) {
        UserDetails userDetails = userDetailsService.loadUser(loginId);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new FailToLoginException(FAIL_TO_LOGIN);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()
        );
        String token = JwtHeader.JWT_HEADER_PREFIX + jwtTokenProvider.createToken(authentication);
        String refreshToken = JwtHeader.JWT_HEADER_PREFIX + jwtTokenProvider.issuedRefreshToken(authentication);
        return new LoginDto(Long.valueOf(userDetails.getUsername()), token, refreshToken);
    }


}
