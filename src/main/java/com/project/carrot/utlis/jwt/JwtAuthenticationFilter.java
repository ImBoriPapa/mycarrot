package com.project.carrot.utlis.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.project.carrot.utlis.jwt.JwtHeader.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = resolveToken(request, AUTHORIZATION_HEADER);
        log.info("Get AUTHORIZATION_HEADER={}",jwt);

        if (jwt == null) {
            log.info("no valid JWT token found, uri: {}", request.getRequestURI());
        }

        if (jwtTokenProvider.validateToken(jwt) ==JwtTokenProvider.JwtCode.ACCESS) {
            log.info("validateToken is Access");
            setJwtTokenProvider(jwt);
            filterChain.doFilter(request, response);
            return;
        }
        //2.AUTHORIZATION_HEADER == EXPIRED is True -> setJwtTokenProvider(jwt)
        if (jwtTokenProvider.validateToken(jwt) == JwtTokenProvider.JwtCode.EXPIRED) {
            //Find REFRESH_HEADER
            String refresh = resolveToken(request, REFRESH_HEADER);
            //REFRESH_HEADER == null -> throw new Exception
            if (refresh == null) {
                log.info("refresh is null");
            }
            //AUTHORIZATION_HEADER == EXPIRED AND RefreshToken == ACCESS
            if (jwtTokenProvider.validateToken(refresh) == JwtTokenProvider.JwtCode.ACCESS) {
                String newRefresh = jwtTokenProvider.reissueRefreshToken(refresh);
                response.setHeader(AUTHORIZATION_HEADER, JWT_HEADER_PREFIX + newRefresh);

                Authentication authentication = jwtTokenProvider.getAuthentication(refresh);
                response.setHeader(AUTHORIZATION_HEADER, JWT_HEADER_PREFIX + jwtTokenProvider.createToken(authentication));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void setJwtTokenProvider(String jwt) {
        Authentication authentication = jwtTokenProvider.getAuthentication(jwt);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String resolveToken(HttpServletRequest request, String header) {
        String bearerToken = request.getHeader(header);
        if (bearerToken != null && bearerToken.startsWith(JWT_HEADER_PREFIX)) {
            log.info("bearerToken={}",bearerToken);
            log.info("bearerToken.substring={}",bearerToken.substring(7));
            return bearerToken.substring(7);
        }
        return null;
    }
}
