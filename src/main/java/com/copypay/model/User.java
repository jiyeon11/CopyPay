package com.copypay.model;

import lombok.Builder;
import lombok.Data;

@Data
public class User {
    private int no;
    private String id;
    private String pw;
    private String email;
    private String otp;
}
