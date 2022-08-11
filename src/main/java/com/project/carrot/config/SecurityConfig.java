package com.project.carrot.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/member/login", "/member/sign-up")
                .permitAll()
                .anyRequest()
                .authenticated();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer()throws Exception{
        return (web) ->{
            web.ignoring()
                    .requestMatchers(PathRequest
                            .toStaticResources() //static resources 접근 허용
                            .atCommonLocations());
        };
    }

}
