package com.copypay.controller;

import com.copypay.dto.MailDto;
import com.copypay.service.MailService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final MailService mailService;

    @GetMapping("/")
    public String login(@RequestParam(value="error", required = false) String error){
        return "login";
    }

    @GetMapping("/main")
    public String home() {
        return "main";
    }

    @GetMapping("/lost-pw")
    public String lostPw() {
        return "lostPw";
    }

    @PostMapping("/mail-send")
    public String sendMail(MailDto mailDto, Model model) {
        try {
            if (mailService.mailSend(mailDto)) {
                return "mailDone"; // 아이디가 존재할 경우
            } else {
                model.addAttribute("error", "존재하지 않는 아이디입니다.");
                return "lostPw";   // 아이디가 존재하지 않을 경우
            }
        } catch (MailSendException e) {
            model.addAttribute("error", "메일 전송 중 오류가 발생했습니다.");
            return "lostPw";
        } catch (Exception e) { // 그 외 예외 처리
            logger.error("예외 발생: {}", e.getMessage());
            model.addAttribute("error", "메일 전송 중 오류가 발생했습니다.");
            return "lostPw";
        }
    }
}