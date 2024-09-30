package com.copypay.controller;

import com.copypay.dto.MailDto;
import com.copypay.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class LoginController {
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
        if(mailService.mailSend(mailDto)){
            return "mailDone"; // 아이디가 존재할 경우
        }else {
            model.addAttribute("error","존재하지 않는 아이디입니다.");
            return "lostPw";   // 아이디가 존재하지 않을 경우
        }
    }

}