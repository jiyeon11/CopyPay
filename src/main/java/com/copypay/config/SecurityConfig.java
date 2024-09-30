package com.copypay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthFailureHandler customAuthFailureHandler;

    @Autowired
    public SecurityConfig(CustomAuthFailureHandler customAuthFailureHandler) {
        this.customAuthFailureHandler = customAuthFailureHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/", "/lost-pw", "/mail-send").permitAll()  // 접근 가능한 페이지
                        .anyRequest().authenticated()                        // 그 외 경로는 로그인 필요
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/")                                           // 로그인 페이지
                        .defaultSuccessUrl("/main", true)  // 로그인 성공 시 이동할 경로
                        .failureHandler(customAuthFailureHandler)                 // 로그인 실패 시
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")         // 로그아웃 시 이동할 경로
                        .invalidateHttpSession(true)); // 세션 만료 처리
        return http.build();
    }

}