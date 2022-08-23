package com.project.carrot.config;

import com.project.carrot.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.net.URLEncoder;

@Configuration
@EnableWebSecurity
@Slf4j
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberService memberService;
    private final DataSource dataSource;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/member/login", "/member/sign-up", "/member/success")
                .permitAll()
                .anyRequest()
                .authenticated().and().csrf().disable();

        http.formLogin()
                .loginPage("/member/login").permitAll()
                .loginProcessingUrl("/member/login")
                .defaultSuccessUrl("/member/success")
                .usernameParameter("loginId")
                .successHandler(
                        new AuthenticationSuccessHandler() {
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                response.sendRedirect("/member/success"); // 인증이 성공한 후에는 root로 이동
                            }
                        })
                .failureHandler(
                        new AuthenticationFailureHandler() {
                            @Override
                            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

                                String message = "알수 없는 오류입니다.";


                                if (exception instanceof UsernameNotFoundException) {
                                    message = "등록된 회원을 찾을 수 없습니다.";
                                } else if (exception instanceof BadCredentialsException) {
                                    message = "비밀번호와 아이디를 확인해주세요";
                                }

                                message = URLEncoder.encode(message, "UTF-8");
                                response.sendRedirect("/member/login?error=true&&message=" + message);

                            }
                        }
                );

        http.logout()
                .logoutSuccessUrl("/");

        http.rememberMe()
                .userDetailsService(memberService)
                .tokenRepository(tokenRepository());

        return http.build();
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
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
