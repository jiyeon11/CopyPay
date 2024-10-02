package com.copypay.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
/*
    로그인 실패 시 username 출력을 위한 클래스
*/
@Component
public class CustomAuthFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthFailureListener.class);

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        String username = event.getAuthentication().getName();
        logger.warn("Failed authentication attempt for user: {}", username);
    }
}
