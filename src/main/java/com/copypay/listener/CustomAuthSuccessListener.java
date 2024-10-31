package com.copypay.listener;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
/*
    로그인 성공 시 username 출력을 위한 클래스
*/
@Slf4j
@Component
public class CustomAuthSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private final HttpSession session;
    public CustomAuthSuccessListener(HttpSession session) {
        this.session = session;
    }
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        LocalDateTime loginTime = LocalDateTime.now();
        session.setAttribute("username", username);
        session.setAttribute("loginTime", loginTime);
        log.info("Success authentication attempt for user: {}", username);
    }
}
