package com.copypay.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
/*
    로그인 성공 시 username 출력을 위한 클래스
*/
@Component
public class CustomAuthSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthSuccessListener.class);

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        logger.info("Success authentication attempt for user: {}", username);
    }
}
