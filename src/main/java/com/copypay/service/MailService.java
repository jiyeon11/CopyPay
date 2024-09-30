package com.copypay.service;

import com.copypay.dto.MailDto;
import com.copypay.model.User;
import com.copypay.repository.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class MailService {
    private final UserMapper userMapper;
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "ksh251024@naver.com";

    public boolean mailSend(MailDto mailDto) {
        User user = userMapper.findByUsername(mailDto.getId());
        if (user == null) {
            return false; // 존재하지 않는 아이디인 경우
        }
        try {
            MailHandler mailHandler = new MailHandler(mailSender);
            user = userMapper.findEmailByUsername(mailDto.getId()); // 사용자가 입력한 아이디로 이메일을 찾아서
            mailHandler.setTo(user.getEmail());                     // 임시비번을 받을 이메일로 지정
            mailHandler.setFrom(MailService.FROM_ADDRESS);
            mailHandler.setSubject(mailDto.getTitle());
            Map<String, Object> params = new HashMap<>();
            params.put("id", mailDto.getId());
            params.put("password", mailDto.getContent());
            userMapper.updatePassword(params); // 임시 비번으로 업데이트
            String htmlContent = "<p>" + mailDto.getContent() + "<p> <img src='cid:sample-img'>";
            mailHandler.setText(htmlContent, true);
            mailHandler.send();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
