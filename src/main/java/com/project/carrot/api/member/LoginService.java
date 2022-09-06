package com.project.carrot.api.member;

import com.project.carrot.domain.member.userDetailsService.UserDetailsServiceImpl;
import com.project.carrot.utlis.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public SingInResponseDto signIn(String loginId,String pw){
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);

        if (!passwordEncoder.matches(pw, userDetails.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()
        );
        return new SingInResponseDto(
                "Bearer-" + jwtTokenProvider.createToken(authentication),
                "Bearer-" + jwtTokenProvider.issuedRefreshToken(authentication));
    }
}
