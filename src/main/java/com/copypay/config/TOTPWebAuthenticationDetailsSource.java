package com.copypay.config;
import com.copypay.repository.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationDetailsSource;

@RequiredArgsConstructor
public class TOTPWebAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, TOTPWebAuthenticationDetails> {
    private final UserMapper userMapper;
    @Override
    public TOTPWebAuthenticationDetails buildDetails(HttpServletRequest request) {
        return new TOTPWebAuthenticationDetails(request, userMapper);
    }

}