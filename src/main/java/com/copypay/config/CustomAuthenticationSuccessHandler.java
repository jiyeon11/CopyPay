package com.copypay.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        TOTPWebAuthenticationDetails details = (TOTPWebAuthenticationDetails) authentication.getDetails();
        if(!details.isValidOtp(authentication.getName())){  //OTP 인증 성공하면 메인으로 감
            response.sendRedirect("/?error=true");
        }else{
            response.sendRedirect("/main");
        }
    }
}
