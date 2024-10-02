package com.copypay.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
/*
    로그인 성공 시 username 출력을 위한 클래스
*/
@Slf4j
@Component
public class CustomAuthSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        log.info("Success authentication attempt for user: {}", username);
    }
}
