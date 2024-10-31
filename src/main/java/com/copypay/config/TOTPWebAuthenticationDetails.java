package com.copypay.config;

import com.copypay.repository.mapper.UserMapper;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

@Getter
public class TOTPWebAuthenticationDetails extends WebAuthenticationDetails {
    private final String secretKey;
    private final UserMapper userMapper;

    @Autowired
    public TOTPWebAuthenticationDetails(HttpServletRequest request, UserMapper userMapper) {
        super(request);
        this.secretKey = request.getParameter("otp");  //OTP 입력값
        this.userMapper = userMapper;
    }

    public boolean isValidOtp(String username) {  //OTP 맞는지 인증
        GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
        String otpKey = userMapper.getOTPKeyById(username); //DB에서 OTP키 가져오기
        return googleAuthenticator.authorize(otpKey, Integer.parseInt(secretKey));
    }
}