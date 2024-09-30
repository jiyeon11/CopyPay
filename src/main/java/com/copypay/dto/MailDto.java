package com.copypay.dto;

import com.copypay.util.RandomPassword;
import lombok.Data;

@Data
public class MailDto {
    private String id;
    private String title = "Copy Pay 임시 비밀번호입니다";
    private String content  = RandomPassword.getRandomPassword(13);
}
