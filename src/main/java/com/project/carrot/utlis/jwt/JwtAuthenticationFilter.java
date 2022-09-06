package com.project.carrot.utlis.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.project.carrot.utlis.jwt.JwtHeader.*;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = resolveToken(request, AUTHORIZATION_HEADER);

        if (jwt != null && jwtTokenProvider.validateToken(jwt) == JwtTokenProvider.JwtCode.ACCESS) {
            Authentication authentication = jwtTokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else if (jwt != null && jwtTokenProvider.validateToken(jwt) == JwtTokenProvider.JwtCode.EXPIRED) {
            String refresh = resolveToken(request, REFRESH_HEADER);

            if (refresh != null && jwtTokenProvider.validateToken(refresh) == JwtTokenProvider.JwtCode.ACCESS) {
                String newRefresh = jwtTokenProvider.reissueRefreshToken(refresh);
                if (newRefresh != null) {
                    response.setHeader(AUTHORIZATION_HEADER, JWT_HEADER_PREFIX+ newRefresh);

                    Authentication authentication = jwtTokenProvider.getAuthentication(refresh);
                    response.setHeader(AUTHORIZATION_HEADER, JWT_HEADER_PREFIX + jwtTokenProvider.createToken(authentication));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } else {
            log.info("no valid JWT token found, uri: {}", request.getRequestURI());
        }
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request, String header) {
        String bearerToken = request.getHeader(header);
        if (bearerToken != null && bearerToken.startsWith(JWT_HEADER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
