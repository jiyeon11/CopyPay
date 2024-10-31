package com.copypay.service;

import com.copypay.dto.MailDto;
import com.copypay.model.User;
import com.copypay.repository.mapper.UserMapper;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {
    private final UserMapper userMapper;
    private final JavaMailSender mailSender;
    private final String fromAddress;

    public MailService(UserMapper userMapper, JavaMailSender mailSender, @Value("${mail.address}") String fromAddress) {
        this.userMapper = userMapper;
        this.mailSender = mailSender;
        this.fromAddress = fromAddress;
    }

    public boolean mailSend(MailDto mailDto) {
        User user = userMapper.findByUsername(mailDto.getId());
        if (user == null) {
            return false; // 존재하지 않는 아이디인 경우
        }
        try {
            MailHandler mailHandler = new MailHandler(mailSender);
            mailHandler.setTo(user.getEmail());  // 임시 비번을 받을 이메일로 지정
            mailHandler.setFrom(fromAddress);
            mailHandler.setSubject(mailDto.getTitle());
            String htmlContent = "<p>" + mailDto.getContent() + "<p> <img src='cid:sample-img'>";
            mailHandler.setText(htmlContent, true);
            mailHandler.send(); // 메일 전송
            Map<String, Object> params = new HashMap<>();
            params.put("id", mailDto.getId());
            params.put("password", mailDto.getContent());
            userMapper.updatePassword(params); // 임시 비번으로 업데이트
            return true;
        }
        catch(Exception e){
            throw new MailSendException("이메일 전송 중 오류 발생", e);
        }
    }

    public boolean mailSendOTP(String id){  //OTP 인증키 QR 코드 메일로 보냄
        User user = userMapper.findByUsername(id);
        GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
        GoogleAuthenticatorKey googleAuthenticatorKey = googleAuthenticator.createCredentials();
        String QRUrl = GoogleAuthenticatorQRGenerator.getOtpAuthURL("CopyPay", user.getId(), googleAuthenticatorKey);
        user.setOtp(googleAuthenticatorKey.getKey());
        try {
            MailHandler mailHandler = new MailHandler(mailSender);
            mailHandler.setTo(user.getEmail());  //QR 코드를 받을 이메일로 지정
            mailHandler.setFrom(fromAddress);
            mailHandler.setSubject(user.getId() + "님 CopyPay OTP 인증키 QR 코드입니다.");
            String htmlContent = "<p>OTP 인증키 QR 코드</p> <img src='"+QRUrl+"'>";
            mailHandler.setText(htmlContent, true);
            mailHandler.send(); // 메일 전송
            return true;
        }
        catch(Exception e){
            throw new MailSendException("이메일 전송 중 오류 발생", e);
        }
    }
}