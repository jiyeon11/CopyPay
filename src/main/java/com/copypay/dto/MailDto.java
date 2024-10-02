package com.copypay.dto;

import com.copypay.util.RandomPassword;
import lombok.Data;

@Data
public class MailDto {
    private String id;
    private String title;
    private String content;

    private static final String DEFAULT_TITLE = "Copy Pay 임시 비밀번호입니다";
    private static final int PASSWORD_LENGTH = 13;

    public MailDto() {
        this.title = DEFAULT_TITLE;
        this.content = RandomPassword.getRandomPassword(PASSWORD_LENGTH);
    }
}
