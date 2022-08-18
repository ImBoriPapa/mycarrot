package com.project.carrot.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/member/login", "/member/sign-up", "/member/success")
                .permitAll()
                .anyRequest()
                .authenticated().and().csrf().disable();

        http.formLogin()
                .loginPage("/member/login").permitAll()
                .defaultSuccessUrl("/member/success")
                .usernameParameter("loginId")
                .successHandler(
                        new AuthenticationSuccessHandler() {
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                response.sendRedirect("/member/success"); // 인증이 성공한 후에는 root로 이동
                            }
                        });

        http.logout()
                .logoutSuccessUrl("/");

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
        return (web) -> {
            web.ignoring()
                    .requestMatchers(PathRequest
                            .toStaticResources() //static resources 접근 허용
                            .atCommonLocations());
        };
    }


}
