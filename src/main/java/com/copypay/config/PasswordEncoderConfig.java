package com.copypay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.util.Base64;

@Configuration
public class PasswordEncoderConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            String PW = ""; // 암호화된 비번이 저장되는 변수
            @Override
            public String encode(CharSequence rawPassword) {
                try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(rawPassword.toString().getBytes());
                    byte byteData[] = md.digest();
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < byteData.length; i++) {
                        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    PW = sb.toString();
                    PW = Base64.getEncoder().encodeToString(PW.getBytes());
                    System.out.println("비번: " + PW);
                    return PW;
                } catch (Exception e) {
                    throw new RuntimeException("MD5 encoding error", e);
                }
            }
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                System.out.println(encode(rawPassword).equals(encodedPassword));
                return encode(rawPassword).equals(encodedPassword);
            }
        };
    }

}
