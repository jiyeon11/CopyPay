package com.copypay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String login(@RequestParam(value="error", required = false) String error){
        return "login";
    }

    @GetMapping("/main")
    public String home() {
        return "main";
    }
}