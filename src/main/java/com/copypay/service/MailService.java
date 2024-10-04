package com.copypay.service;

import com.copypay.dto.MailDto;
import com.copypay.model.User;
import com.copypay.repository.mapper.UserMapper;
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
}